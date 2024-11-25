package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.enums.EGrade;
import by.vstu.dean.enums.ESemester;
import by.vstu.dean.enums.ExamType;
import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.models.merge.StatementTeacherMerge;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StatementStudentMergeRepository;
import by.vstu.dean.repo.StatementTeacherMergeRepository;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.StatementService;
import by.vstu.dean.services.StudyPlanService;
import by.vstu.dean.services.TeacherService;
import by.vstu.dean.services.students.StudentService;
import by.vstu.old.dean.models.DStatementModel;
import by.vstu.old.dean.repo.DStatementModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementMigrateService extends BaseMigrateService<StatementModel, DStatementModel> {

    private final DStatementModelRepository dStatementModelRepository;
    private final StatementService statementService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final StudyPlanService studyPlanService;
    private final TeacherService teacherService;
    private final StatementStudentMergeRepository statementStudentMergeRepository;
    private final StatementTeacherMergeRepository statementTeacherMergeRepository;

    private List<TeacherModel> teacherModels;
    private List<StudyPlanModel> studyPlanModels;

    @Override
    public void init() {
        teacherModels = this.teacherService.getAll();
        studyPlanModels = this.studyPlanService.getAll();
    }

    public Optional<TeacherModel> findTeacherByStudyPlanSourceId(Long id) {
        if (id == null)
            return Optional.empty();
        return this.studyPlanModels.stream().filter(p -> p.getTeacher() != null && p.getSourceId().equals(id)).map(StudyPlanModel::getTeacher).findFirst();
    }

    public Optional<TeacherModel> findTeacherBySourceId(Long id) {
        if (id == null)
            return Optional.empty();
        return this.teacherModels.stream().filter(p -> p.getSourceId().equals(id)).findFirst();
    }

    public Optional<StudyPlanModel> findStudyPlanBySourceId(Long id) {
        if (id == null)
            return Optional.empty();
        return this.studyPlanModels.stream().filter(p -> p.getSourceId().equals(id)).findFirst();
    }

    @Override
    public Long getLastDBId() {
        return this.statementService.getRepo().findTopByOrderByIdDesc() == null ? 0L : this.statementService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<StatementModel> convertNotExistsFromDB() {

//        List<StatementModel> converted = this.statementService.getAll();
        List<StatementModel> out = Collections.synchronizedList(new ArrayList<>());

        //У студента овердохера данных, а нам нужен только sourceId
        this.studyPlanService.getAllSourceIds().parallelStream().filter(p -> !this.statementService.hasStatement(p)).forEach((studyPlanSourceId) -> {
            /*
             * Пробуем хитрый метод.
             * Мы делаем выборку всех id ведомостей студентов и выбираем все записи, что у нас не существуют для этого студента.
             * Теоретически, это должно очень сильно увеличить производительность при последующих миграциях и обновлениях (если это сработает).
             */
            List<DStatementModel> studyPlanStatements = this.dStatementModelRepository.findByStudyPlanId(studyPlanSourceId);
            //нам нужно создать копию для работы
            log.info("Processing studyPlan with sourceId {}", studyPlanSourceId);
            Optional<DStatementModel> oStudyPlanStatement = studyPlanStatements.stream().findFirst();
            oStudyPlanStatement.ifPresent(dStatementModel -> out.add(this.convertSingle(dStatementModel, false)));

            if (oStudyPlanStatement.isEmpty())
                log.warn("DStatementModel not found for studyPlan with sourceId {}", studyPlanSourceId);

        });

//        this.studyPlanService.getAll().forEach((studyPlanModel) -> {
//            this.dStatementModelRepository.findAllByStudyPlanId(studyPlanModel.getSourceId()).forEach((dStatementModel) -> {
//                //нам нужно создать копию для работы
//                List<StatementModel> tempList = new ArrayList<>(out);
//                if(tempList.stream()
//                        .noneMatch(p -> p.getGroupStatementNumber() != null &&
//                                p.getGroupStatementNumber().equals(dStatementModel.getFacultyStatementNumber())
//                                        && p.getStudyPlan().getGroup().getSourceId().equals(dStatementModel.getStudyPlan().getGroup().getId())
//                        )
//                )
//                    out.add(this.convertSingle(dStatementModel, false));
//            });
//        });


        return out.stream().filter(Objects::nonNull).toList();
    }

    @Override
    public StatementModel convertSingle(DStatementModel dStatementModel, boolean update) {

        Optional<StudyPlanModel> oStudyPlan = Optional.ofNullable(this.studyPlanService.getBySourceId(dStatementModel.getStudyPlanId()));

        if (oStudyPlan.isEmpty()) {
            //есть "крутые студенты", что отчислялись и восстанавливались с 2008 года. кайф
            //просто забиваем на эту ведомость, т.к. учебный план 400% битый для привязанной группы
            log.warn("Can't find study plan for id {}", dStatementModel.getId());
            return null;
        }

        StatementModel statementModel = new StatementModel();

        statementModel.setStatementDate(dStatementModel.getStatementDate() != null ? dStatementModel.getStatementDate().toLocalDate() : LocalDate.now());
        statementModel.setStudyPlan(oStudyPlan.get());
        statementModel.setGroupStatementNumber(dStatementModel.getGlobalStatementNumber());
        statementModel.setCourse(dStatementModel.getCourse());

        Integer semesterNumber = dStatementModel.getSemesterNumber();

        ESemester semesterType = ESemester.UNKNOWN;

        if (semesterNumber != null) {
            // если номер семестра можно разделить на 2, то это 100% весенний семестр
            if (semesterNumber % 2 == 0)
                semesterType = ESemester.SPRING;
            else
                semesterType = ESemester.AUTUMN;
        }

        statementModel.setSemesterType(semesterType);

        statementModel.setSourceId(dStatementModel.getId());
        statementModel.setUpdated(LocalDateTime.now());
        if (dStatementModel.getGroupCurrentCourse() != null)
            statementModel.setStatus(dStatementModel.getGroupCurrentCourse() != 99 ? EStatus.ACTIVE : EStatus.DELETED);
        else
            statementModel.setStatus(EStatus.DELETED);

        if (!update)
            statementModel.setCreated(LocalDateTime.now());

        return statementModel;
    }

    public List<StatementStudentMerge> analyseAndCreateMerges() {

        List<StatementStudentMerge> out = new ArrayList<>();
        List<Long> temp = this.statementStudentMergeRepository.findSourceIds();

        this.statementService.getRepo().findAll().forEach((statementModel) -> {
            //Это очень страшно)
            List<DStatementModel> oldStatements = this.dStatementModelRepository.findAllByStudyPlanId(statementModel.getStudyPlan().getSourceId());

            oldStatements.parallelStream().filter(p -> !temp.contains(p.getId())).forEach((oldStatement) -> {

                Optional<TeacherModel> oTeacher1 = this.findTeacherBySourceId(oldStatement.getRetakeTeacherId1());
                Optional<TeacherModel> oTeacher2 = this.findTeacherBySourceId(oldStatement.getRetakeTeacherId2());
                Optional<TeacherModel> oTeacher3 = this.findTeacherBySourceId(oldStatement.getRetakeTeacherId3());
                Optional<TeacherModel> oTeacher4 = this.findTeacherByStudyPlanSourceId(statementModel.getStudyPlan().getSourceId());


                Optional<StudentModel> oStudent = Optional.ofNullable(this.studentService.getBySourceId(oldStatement.getStudentId() != null ? oldStatement.getStudentId() : 0L));
                //нет студента = нет записи
                if (oStudent.isPresent()) {
                    //да. это всё только для того, чтобы привести весь этот бред к enum ;)
                    EGrade grade = EGrade.UNKNOWN;
                    if (oldStatement.getGrade() != null)
                        if (oldStatement.getGrade().getExamType().equals("зачет")) {
                            if (oldStatement.getGrade().getGrade().equalsIgnoreCase("зачет"))
                                grade = EGrade.PASS;
                            else if (oldStatement.getGrade().getGrade().equalsIgnoreCase("незачет"))
                                grade = EGrade.NOT_PASS;
                            else if (oldStatement.getGrade().getGrade().equalsIgnoreCase("недоп"))
                                grade = EGrade.NOT_ALLOWED;
                            else if (oldStatement.getGrade().getGrade().equalsIgnoreCase("неявка"))
                                grade = EGrade.NO_SHOW;
                        } else {
                            int gradeInt = -3; // неизвестный

                            if (oldStatement.getGrade().getGrade().equalsIgnoreCase("недоп"))
                                gradeInt = -1;
                            else if (oldStatement.getGrade().getGrade().equalsIgnoreCase("неявка"))
                                gradeInt = -2;
                            else if (StringUtils.canBeInt(oldStatement.getGrade().getGrade()))
                                gradeInt = Integer.parseInt(oldStatement.getGrade().getGrade());
                            grade = EGrade.findBy(oldStatement.getGrade().getRange(), oldStatement.getGrade().getExamType().equalsIgnoreCase("экзамен") ? ExamType.EXAM : ExamType.TEST, gradeInt);
                        }

                    LocalDate passDate = oldStatement.getPassDate() != null ? oldStatement.getPassDate().toLocalDate() : LocalDate.parse("2001-09-11");
                    int attempt = 1;
                    if (oldStatement.getRetakeDate1() != null && oldStatement.getRetakeDate1().toLocalDate().isAfter(passDate)) {
                        passDate = oldStatement.getRetakeDate1().toLocalDate();
                        attempt = 2;
                    }

                    if (oldStatement.getRetakeDate2() != null && oldStatement.getRetakeDate2().toLocalDate().isAfter(passDate)) {
                        passDate = oldStatement.getRetakeDate2().toLocalDate();
                        attempt = 3;
                    }

                    if (oldStatement.getRetakeDate3() != null && oldStatement.getRetakeDate3().toLocalDate().isAfter(passDate)) {
                        passDate = oldStatement.getRetakeDate3().toLocalDate();
                        attempt = 4;
                    }

                    Integer sheetNumber = oldStatement.getSemesterNumber();

                    if (oldStatement.getRetakeExamSheetNumber1() != null)
                        sheetNumber = oldStatement.getRetakeExamSheetNumber1();

                    if (oldStatement.getRetakeExamSheetNumber2() != null)
                        sheetNumber = oldStatement.getRetakeExamSheetNumber2();

                    if (oldStatement.getRetakeExamSheetNumber3() != null)
                        sheetNumber = oldStatement.getRetakeExamSheetNumber3();


                    List<TeacherModel> teacherModels = new ArrayList<>();


                    //Я просто не знаю как тут сделать иначе
                    oTeacher1.ifPresent(teacherModels::add);
                    oTeacher2.ifPresent(teacherModels::add);
                    oTeacher3.ifPresent(teacherModels::add);

                    if (oTeacher4.isPresent() && teacherModels.stream().noneMatch(p -> p.getId().equals(oTeacher4.get().getId())))
                        oTeacher4.ifPresent(teacherModels::add);


                    out.add(this.createMergeModel(statementModel, teacherModels, oStudent.get(), grade, passDate, attempt, oldStatement.getId(), sheetNumber));

                    if (oTeacher1.isEmpty() && oTeacher2.isEmpty() && oTeacher3.isEmpty() && oTeacher4.isEmpty()) {
                        log.warn("No one teacher is present in DStatementModel with id {}", oldStatement.getId());
                    }
                } else
                    log.warn("No student is present in DStatementModel with id {}", oldStatement.getId());

            });
        });

//        List<StatementStudentMerge> allMerges = this.statementStudentMergeRepository.findAll();
//.stream().filter(p -> allMerges.stream().noneMatch(e -> e.getSourceId().equals(p.getSourceId()) && p.getAttemptNumber().equals(e.getAttemptNumber()) && p.getTeachers().size() != e.getTeachers().size())).toList()
        return out;
    }


    public StatementStudentMerge createMergeModel(StatementModel model, List<TeacherModel> teachers, StudentModel student, EGrade grade, LocalDate passDate, Integer attemptNumber, Long sourceId, Integer sheetNumber) {
        StatementStudentMerge mergeModel = new StatementStudentMerge();

        mergeModel.setStatement(model);
        mergeModel.setStatus(teachers.isEmpty() ? EStatus.BROKEN : model.getStatus());
        mergeModel.setCreated(model.getCreated());
        mergeModel.setUpdated(model.getUpdated());
        mergeModel.setSourceId(sourceId);
        mergeModel.setStudent(student);
        //Теоретически, сдавать могут разным преподавателям
//        mergeModel.setTeachers(teachers);
        mergeModel.setSheetNumber(sheetNumber);
        mergeModel.setGrade(grade);
        mergeModel.setAttemptNumber(attemptNumber);
        //Если это не первая попытка, то это пересдача
        mergeModel.setRetake(attemptNumber > 1);
        mergeModel.setPassDate(passDate);

        //это очень страшно
        mergeModel = this.statementStudentMergeRepository.saveAndFlush(mergeModel);

        StatementStudentMerge finalMergeModel = mergeModel;
        finalMergeModel.setTeachers(teachers.stream().map(m -> {

            StatementTeacherMerge stm = new StatementTeacherMerge();
            stm.setSsm(finalMergeModel);
            stm.setTeacher(m);
            stm.setSourceId(sourceId);
            stm.setCreated(model.getCreated());
            stm.setUpdated(model.getUpdated());

            return stm;
        }).toList());

        return finalMergeModel;
    }

    @Override
    public StatementModel insertSingle(StatementModel t) {
        return this.statementService.save(t);
    }

    @Override
    public List<StatementModel> insertAll(List<StatementModel> t) {
        return this.statementService.saveAll(t);
    }

    @Override
    public void migrate() {
        //оно очееееень медленное, но работает
        this.insertAll(this.convertNotExistsFromDB());
        log.info("Saving students for statements");
        List<StatementStudentMerge> ssm = this.analyseAndCreateMerges();
        this.statementTeacherMergeRepository.saveAllAndFlush(ssm.stream().flatMap(m -> m.getTeachers().stream()).toList());
        this.statementStudentMergeRepository.saveAll(ssm);
    }
}
