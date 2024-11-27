package by.vstu.old.dean.services.update;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.enums.EGrade;
import by.vstu.dean.enums.ExamType;
import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.models.merge.StatementTeacherMerge;
import by.vstu.dean.repo.StatementModelRepository;
import by.vstu.dean.repo.StatementStudentMergeRepository;
import by.vstu.dean.repo.StatementTeacherMergeRepository;
import by.vstu.dean.services.StatementService;
import by.vstu.dean.services.StudyPlanService;
import by.vstu.dean.services.TeacherService;
import by.vstu.old.dean.models.DStatementModel;
import by.vstu.old.dean.repo.DStatementModelRepository;
import by.vstu.old.dean.services.migrate.StatementMigrateService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
public class StatementUpdateService extends BaseUpdateService<DStatementModel, DStatementModelRepository, StatementModel, StatementModelRepository, StatementService, StatementMigrateService> {

    private final StatementService statementService;
    private final TeacherService teacherService;
    private final StudyPlanService studyPlanService;
    private final StatementStudentMergeRepository statementStudentMergeRepository;
    private final StatementTeacherMergeRepository statementTeacherMergeRepository;

    public StatementUpdateService(StatementModelRepository repo, DStatementModelRepository dRepo, StatementMigrateService baseMigrateService, StatementService service, StatementService statementService, TeacherService teacherService, StudyPlanService studyPlanService, StatementStudentMergeRepository statementStudentMergeRepository, StatementTeacherMergeRepository statementTeacherMergeRepository) {
        super(repo, dRepo, baseMigrateService, service);
        this.statementService = statementService;
        this.teacherService = teacherService;
        this.studyPlanService = studyPlanService;
        this.statementStudentMergeRepository = statementStudentMergeRepository;
        this.statementTeacherMergeRepository = statementTeacherMergeRepository;
    }

    @SneakyThrows
    public List<StatementStudentMerge> getUpdatesForSSM() throws ExecutionException, InterruptedException {
        long totalUsers = this.statementStudentMergeRepository.countAll(); // Общее количество пользователей
        int pageSize = 20000; // Количество пользователей на страницу
        long totalPages = (long) Math.ceil((double) totalUsers / pageSize);

        List<StatementStudentMerge> out = Collections.synchronizedList(new ArrayList<>());
        List<StatementTeacherMerge> outTeachers = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<List<StatementStudentMerge>>> futures = new ArrayList<>();

        for (int page = 0; page < totalPages; page++) {
            final int currentPage = page;
            Callable<List<StatementStudentMerge>> task = () -> this.statementStudentMergeRepository.findAll(PageRequest.of(currentPage, pageSize)).get().toList();
            futures.add(executor.submit(task));
        }


        futures.parallelStream().map(m -> {
            try {
                return m.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).forEach(statement -> {
            List<StatementTeacherMerge> outTeachersTemp = new ArrayList<>(outTeachers);
            log.info("Updating StudentStatement with id {}", statement.getId());
            Optional<DStatementModel> oOldStatement = this.dRepo.findById(statement.getSourceId());

            if (oOldStatement.isPresent()) {
                DStatementModel oldStatement = oOldStatement.get();

                Long tId1 = oldStatement.getRetakeTeacherId1() != null ? oldStatement.getRetakeTeacherId1() : 0L;
                Long tId2 = oldStatement.getRetakeTeacherId2() != null ? oldStatement.getRetakeTeacherId2() : 0L;
                Long tId3 = oldStatement.getRetakeTeacherId3() != null ? oldStatement.getRetakeTeacherId3() : 0L;
                Long tId4 = statement.getStatement().getStudyPlan().getId();


                Optional<TeacherModel> oTeacher1 = statement.getTeachers().stream().map(StatementTeacherMerge::getTeacher).filter(p -> p.getSourceId().equals(tId1)).findFirst();
                if (oTeacher1.isEmpty())
                    oTeacher1 = Optional.ofNullable(this.teacherService.getBySourceId(tId1));
                Optional<TeacherModel> oTeacher2 = statement.getTeachers().stream().map(StatementTeacherMerge::getTeacher).filter(p -> p.getSourceId().equals(tId1)).findFirst();
                if (oTeacher2.isEmpty())
                    oTeacher2 = Optional.ofNullable(this.teacherService.getBySourceId(tId2));
                Optional<TeacherModel> oTeacher3 = statement.getTeachers().stream().map(StatementTeacherMerge::getTeacher).filter(p -> p.getSourceId().equals(tId1)).findFirst();
                if (oTeacher3.isEmpty())
                    oTeacher3 = Optional.ofNullable(this.teacherService.getBySourceId(tId3));
                Optional<TeacherModel> oTeacher4 = statement.getTeachers().stream().map(StatementTeacherMerge::getTeacher).filter(p -> p.getSourceId().equals(tId1)).findFirst();
                if (oTeacher4.isEmpty())
                    oTeacher4 = this.studyPlanService.getRepo().findTeacherByStudyPlanId(tId4);

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

                if (oldStatement.getStatementDate() != null && passDate.equals(LocalDate.now())) {
                    passDate = oldStatement.getStatementDate().toLocalDate();
                }

                Integer sheetNumber = oldStatement.getSemesterNumber();

                if (oldStatement.getRetakeExamSheetNumber1() != null)
                    sheetNumber = oldStatement.getRetakeExamSheetNumber1();

                if (oldStatement.getRetakeExamSheetNumber2() != null)
                    sheetNumber = oldStatement.getRetakeExamSheetNumber2();

                if (oldStatement.getRetakeExamSheetNumber3() != null)
                    sheetNumber = oldStatement.getRetakeExamSheetNumber3();


                List<StatementTeacherMerge> teacherModels = new ArrayList<>(statement.getTeachers());
                //Я просто не знаю как тут сделать иначе
                oTeacher1.ifPresent(t -> {
                    if (teacherModels.stream().noneMatch(p -> p.getTeacher().getSourceId().equals(t.getSourceId())))
                        teacherModels.add(this.createTeacher(t, statement));
                });
                oTeacher2.ifPresent(t -> {
                    if (teacherModels.stream().noneMatch(p -> p.getTeacher().getSourceId().equals(t.getSourceId())))
                        teacherModels.add(this.createTeacher(t, statement));
                });
                oTeacher3.ifPresent(t -> {
                    if (teacherModels.stream().noneMatch(p -> p.getTeacher().getSourceId().equals(t.getSourceId())))
                        teacherModels.add(this.createTeacher(t, statement));
                });

                if (oTeacher4.isPresent() && teacherModels.stream().noneMatch(p -> p.getSourceId().equals(tId4)))
                    oTeacher4.ifPresent(t -> teacherModels.add(this.createTeacher(t, statement)));

                if (!teacherModels.isEmpty()) {
//                    outTeachers.addAll(teacherModels.stream().filter(p -> outTeachersTemp.stream().noneMatch(e -> p.getTeacher().getSourceId().equals(e.getTeacher().getSourceId()))).toList());
                    statement.setStatus(EStatus.ACTIVE);
                }
                statement.setGrade(grade);
                statement.setSheetNumber(sheetNumber);
                statement.setPassDate(passDate);
                statement.setAttemptNumber(attempt);
            } else {
                statement.setStatus(EStatus.DELETED);
            }

            out.add(statement);

        });

        log.info("Saving STM with size {}", outTeachers.size());
        this.saveParallelTeachers(outTeachers);

        return out;
    }

    private StatementTeacherMerge createTeacher(TeacherModel teacher, StatementStudentMerge statementStudentMerge) {
        StatementTeacherMerge stm = new StatementTeacherMerge();
        stm.setSsm(statementStudentMerge);
        stm.setStatus(statementStudentMerge.getStatus());
        stm.setTeacher(teacher);
        stm.setSourceId(statementStudentMerge.getSourceId());
        stm.setCreated(statementStudentMerge.getCreated());
        stm.setUpdated(LocalDateTime.now());
        return stm;
    }

    private List<StatementTeacherMerge> saveParallelTeachers(List<StatementTeacherMerge> list) {
        long totalUsers = list.size(); // Общее количество пользователей
        int pageSize = 25000; // Количество пользователей на страницу
        long totalPages = (long) Math.ceil((double) totalUsers / pageSize);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<List<StatementTeacherMerge>>> futures = new ArrayList<>();

        for (int page = 0; page < totalPages; page++) {
            final int currentPage = page;
            Callable<List<StatementTeacherMerge>> task = () -> this.statementTeacherMergeRepository.saveAll(list.subList((currentPage * pageSize), (int) ((long) (currentPage + 1) * pageSize < totalUsers ? (currentPage + 1) * pageSize : totalUsers)));
            futures.add(executor.submit(task));
        }

        return futures.parallelStream().map(m -> {
            try {
                return m.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).toList();
    }

    private List<StatementStudentMerge> saveParallelStudents(List<StatementStudentMerge> list) {
        long totalUsers = list.size(); // Общее количество пользователей
        int pageSize = 40000; // Количество пользователей на страницу
        long totalPages = (long) Math.ceil((double) totalUsers / pageSize);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Future<List<StatementStudentMerge>>> futures = new ArrayList<>();

        for (int page = 0; page < totalPages; page++) {
            final int currentPage = page;
            Callable<List<StatementStudentMerge>> task = () -> this.statementStudentMergeRepository.saveAll(list.subList((currentPage * pageSize), (int) ((long) (currentPage + 1) * pageSize < totalUsers ? (currentPage + 1) * pageSize : totalUsers)));
            futures.add(executor.submit(task));
        }

        return futures.parallelStream().map(m -> {
            try {
                return m.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).toList();
    }


    @Override
//    @PostConstruct
    public void onInit() {
        log.info("SSM update service started");

//        new Thread(() -> {
//            try {
//                List<StatementStudentMerge> statementStudentMerges = this.getUpdatesForSSM();
//                log.info("Saving SSM with size {}", statementStudentMerges.size());
//                this.saveParallelStudents(statementStudentMerges);
//                log.info("SSM done!");
//            } catch (ExecutionException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();


    }


    @Override
    public void update() {

    }
}
