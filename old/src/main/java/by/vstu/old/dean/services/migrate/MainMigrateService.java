package by.vstu.old.dean.services.migrate;

import by.vstu.old.dean.services.update.MainUpdateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainMigrateService {

    private final List<IMigrateExecutor> services = new ArrayList<>();

    private final TeacherDegreeMigrateService teacherDegreeMigrateService;
    private final TeacherMigrateService teacherMigrateService;
    private final ExamTypeMigrateService examTypeMigrateService;
    private final CitizenshipMigrateService citizenshipMigrateService;
    private final DepartmentMigrateService departmentMigrateService;
    private final DisciplineMigrateService disciplineMigrateService;
    private final StudentLanguageMigrateService studentLanguageMigrateService;
    private final InstitutionMigrateService institutionMigrateService;
    private final QualificationMigrateService qualificationMigrateService;
    private final FacultyMigrateService facultyMigrateService;
    private final SpecialityMigrateService specialityMigrateService;
    private final SpecializationMigrateService specializationMigrateService;
    private final GroupMigrateService groupMigrateService;
    private final StudentMigrateService studentMigrateService;
    private final StudyPlanMigrateService studyPlanMigrateService;
    private final EducationMigrateService educationMigrateService;
    private final DepartmentSpecialityMergeService departmentSpecialityMergeService;
    private final AbsenceMigrateService absenceMigrateService;
    private final TeacherDepartmentMigrateService teacherDepartmentMigrateService;
    private final MainUpdateService mainUpdateService;

    @PostConstruct
    public void migrate() {
        long startTime = System.currentTimeMillis();
        log.info("Migrate started at {}", startTime);

        Thread migrateThread = new Thread(() -> {
            services.add(this.teacherDegreeMigrateService);
            services.add(this.teacherMigrateService);
            services.add(this.examTypeMigrateService);
            services.add(this.citizenshipMigrateService);
            services.add(this.studentLanguageMigrateService);
            services.add(this.institutionMigrateService);
            services.add(this.qualificationMigrateService);
            services.add(this.facultyMigrateService);
            services.add(this.departmentMigrateService);
            services.add(this.disciplineMigrateService);
            services.add(this.specialityMigrateService);
            services.add(this.specializationMigrateService);
            services.add(this.groupMigrateService);
            services.add(this.studentMigrateService);
            services.add(this.studyPlanMigrateService);
            services.add(this.absenceMigrateService);
//            services.add(this.departmentSpecialityMergeService);
//            services.add(this.teacherDepartmentMigrateService);
            log.info("Initializing services...");
            services.forEach(IMigrateExecutor::init);
            log.info("Migrating...");
            services.forEach(IMigrateExecutor::migrate);

            log.info("Migrate end at {} with time {}s.", System.currentTimeMillis(), Math.floor(((double) System.currentTimeMillis() - (double) startTime) / 1000D));
            long updateStartTime = System.currentTimeMillis();

            log.info("Update started at {}", updateStartTime);

            this.mainUpdateService.update();

            log.info("Update end at {} with time {}s", System.currentTimeMillis(), Math.floor(((double) System.currentTimeMillis() - (double) updateStartTime) / 1000D));

            log.info("Applying spec for groups");
            this.groupMigrateService.insertAll(this.groupMigrateService.applySpecIdByStudents());
            log.info("Applying student for educationModels");
            this.educationMigrateService.insertAll(this.educationMigrateService.applyStudentIds());

            log.info("Cleanup...");
            services.forEach(IMigrateExecutor::cleanup);
            log.info("Migrate/Update done!");
        });

        migrateThread.setName("Migration");
//        migrateThread.start();

    }

}
