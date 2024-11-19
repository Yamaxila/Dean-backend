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
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StatementStudentMergeRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatementMigrateService extends BaseMigrateService<StatementModel, DStatementModel> {

    private final DStatementModelRepository dStatementModelRepository;
    private final StatementService statementService;
    private final StudentService studentService;
    private final StudyPlanService studyPlanService;
    private final TeacherService teacherService;
    private final StatementStudentMergeRepository statementStudentMergeRepository;


    @Override
    public Long getLastDBId() {
        return this.statementService.getRepo().findTopByOrderByIdDesc() == null ? 0L : this.statementService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<StatementModel> convertNotExistsFromDB() {

        List<StatementModel> converted = this.statementService.getAll();
        List<StatementModel> out = new ArrayList<>();

        //У студента овердохера данных, а нам нужен только sourceId
        this.studentService.getAllSourceIds().forEach((studentSourceId) -> {
            /*
             * Пробуем хитрый метод.
             * Мы делаем выборку всех id ведомостей студентов и выбираем все записи, что у нас не существуют для этого студента.
             * Теоретически, это должно очень сильно увеличить производительность при последующих миграциях и обновлениях (если это сработает).
             */
            List<DStatementModel> studentStatements = this.dStatementModelRepository.findAllByStudentIdAndIdNotIn(studentSourceId, this.statementService.getAllDistinctSourceIdForStudent(studentSourceId));
            //нам нужно создать копию для работы
            List<StatementModel> tempList = new ArrayList<>(out);
            studentStatements.stream()
                    .filter(Objects::nonNull)
                    // p - старый объект
                    // e - сконвертированный объект
                    // тут удаляем все дубликаты
                    .filter(p -> converted.stream().filter(Objects::nonNull).noneMatch(e -> e.getSourceId().equals(p.getId()) //если id совпадает
                                            // ИЛИ
                                            //Сверяем номер ведомости + группу вместе
                                            || (
                                            Objects.equals(e.getGroupStatementNumber(), p.getGlobalStatementNumber()) //Сверяем номера ведомостей
                                                    && (
                                                    e.getStudyPlan() != null
                                                            && e.getStudyPlan().getGroup().getSourceId().equals(p.getGroupId())
                                            ) //Так же группа должна совпасть
                                    )
                            )
                    ) //Это выглядит очень страшно, но я не хочу считать скобочки)
                    .filter(p -> tempList.stream().filter(Objects::nonNull).noneMatch(e -> e.getSourceId().equals(p.getId()) //если id совпадает
                                            // ИЛИ
                                            //Сверяем номер ведомости + группу вместе
                                            || (
                                            Objects.equals(e.getGroupStatementNumber(), p.getGlobalStatementNumber()) //Сверяем номера ведомостей
                                                    && (
                                                    e.getStudyPlan() != null
                                                            && e.getStudyPlan().getGroup().getSourceId().equals(p.getGroupId())
                                            ) //Так же группа должна совпасть
                                    )
                            )
                    ) //Это выглядит очень страшно, но я не хочу считать скобочки)
                    .forEach((studentStatement) -> {
                        out.add(this.convertSingle(studentStatement, false));
                    });

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
            log.info("Can't find study plan for id {}", dStatementModel.getId());
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

        this.statementService.getRepo().findAll().forEach((statementModel) -> {
            //Это очень страшно)
            List<DStatementModel> oldStatements = this.dStatementModelRepository.findAllByMagic(
                    statementModel.getCourse(), this.studyPlanService.getRepo().findSourceId(statementModel.getStudyPlan().getId()),
                    this.studyPlanService.getRepo().findFacultySourceId(statementModel.getStudyPlan().getId()), statementModel.getGroupStatementNumber());

            oldStatements.forEach((oldStatement) -> {

                Optional<TeacherModel> oTeacher1 = Optional.ofNullable(this.teacherService.getBySourceId(oldStatement.getRetakeTeacherId1() != null ? oldStatement.getRetakeTeacherId1() : 0L));
                Optional<TeacherModel> oTeacher2 = Optional.ofNullable(this.teacherService.getBySourceId(oldStatement.getRetakeTeacherId2() != null ? oldStatement.getRetakeTeacherId2() : 0L));
                Optional<TeacherModel> oTeacher3 = Optional.ofNullable(this.teacherService.getBySourceId(oldStatement.getRetakeTeacherId3() != null ? oldStatement.getRetakeTeacherId3() : 0L));
                Optional<TeacherModel> oTeacher4 = this.studyPlanService.getRepo().findTeacherByStudyPlanId(statementModel.getStudyPlan().getId());


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

                    LocalDate passDate = oldStatement.getPassDate() != null ? oldStatement.getPassDate().toLocalDate() : LocalDate.now();

                    //Я просто не знаю как тут сделать иначе
                    if (oTeacher1.isPresent())
                        out.add(this.createMergeModel(statementModel, oTeacher1.get(), oStudent.get(), grade, passDate, 1, oldStatement.getId()));

                    if (oTeacher4.isPresent())
                        out.add(this.createMergeModel(statementModel, oTeacher4.get(), oStudent.get(), grade, passDate, 1, oldStatement.getId()));

                    if (oTeacher2.isPresent())
                        out.add(this.createMergeModel(statementModel, oTeacher2.get(), oStudent.get(), grade, passDate, 2, oldStatement.getId()));

                    if (oTeacher3.isPresent())
                        out.add(this.createMergeModel(statementModel, oTeacher3.get(), oStudent.get(), grade, passDate, 3, oldStatement.getId()));

                    if (oTeacher1.isEmpty() && oTeacher2.isEmpty() && oTeacher3.isEmpty() && oTeacher4.isEmpty()) {
                        log.error("No one teacher is present in DStatementModel with id {}", oldStatement.getId());
                        out.add(this.createMergeModel(statementModel, null, oStudent.get(), grade, passDate, 0, oldStatement.getId()));
                    }
                } else
                    log.error("No student is present in DStatementModel with id {}", oldStatement.getId());

            });
        });

        List<StatementStudentMerge> allMerges = this.statementStudentMergeRepository.findAll();

        return out.stream().filter(p -> allMerges.stream().noneMatch(e -> e.getSourceId().equals(p.getSourceId()) && p.getAttemptNumber().equals(e.getAttemptNumber()))).toList();
    }

    public StatementStudentMerge createMergeModel(StatementModel model, TeacherModel teacher, StudentModel student, EGrade grade, LocalDate passDate, Integer attemptNumber, Long sourceId) {
        StatementStudentMerge mergeModel = new StatementStudentMerge();

        mergeModel.setStatement(model);
        mergeModel.setStatus(teacher == null ? EStatus.BROKEN : model.getStatus());
        mergeModel.setCreated(model.getCreated());
        mergeModel.setUpdated(model.getUpdated());
        mergeModel.setSourceId(sourceId);

        mergeModel.setStudent(student);
        //Теоретически, сдавать могут разным преподавателям
        mergeModel.setTeacher(teacher);
        mergeModel.setGrade(grade);
        mergeModel.setAttemptNumber(attemptNumber);
        //Если это не первая попытка, то это пересдача
        mergeModel.setRetake(attemptNumber != 1);
        mergeModel.setPassDate(passDate);
        return mergeModel;
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
        this.statementStudentMergeRepository.saveAllAndFlush(this.analyseAndCreateMerges());
    }
}
