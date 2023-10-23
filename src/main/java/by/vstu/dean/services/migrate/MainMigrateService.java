package by.vstu.dean.services.migrate;

import by.vstu.dean.services.updates.MainUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
            services.add(this.departmentSpecialityMergeService);
            services.add(this.teacherDepartmentMigrateService);


//            services.forEach(IMigrateExecutor::migrate);
//
//            System.err.println("Migration time(s): " + Math.floor(((double) System.currentTimeMillis() - (double) startTime) / 1000D));
//            long updateStartTime = System.currentTimeMillis();
//
//            this.mainUpdateService.update();
//            System.err.println("Update time(s): " + Math.floor(((double) System.currentTimeMillis() - (double) updateStartTime) / 1000D));
//
//            System.out.println("Applying spec for groups");
//            this.groupMigrateService.insertAll(this.groupMigrateService.applySpecIdByStudents());
//            System.out.println("Applying student for educationModels");
//            this.educationMigrateService.insertAll(this.educationMigrateService.applyStudentIds());

        });

        migrateThread.setName("Migration");
        migrateThread.start();

    }

}
