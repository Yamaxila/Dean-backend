package by.vstu.migrate.v1.service.migrate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final StudentLanguageMigrateService studentLanguageMigrateService;
    private final InstitutionMigrateService institutionMigrateService;
    private final QualificationMigrateService qualificationMigrateService;
    private final FacultyMigrateService facultyMigrateService;
    private final HostelRoomMigrateService hostelRoomMigrateService;
    private final DepartmentMigrateService departmentMigrateService;
    private final ClassroomsMigrateService classroomsMigrateService;
    private final DisciplineMigrateService disciplineMigrateService;
    private final SpecialityMigrateService specialityMigrateService;
    private final SpecializationMigrateService specializationMigrateService;
    private final GroupMigrateService groupMigrateService;
    private final StudentMigrateService studentMigrateService;
    private final StudyPlanMigrateService studyPlanMigrateService;
    private final AbsenceMigrateService absenceMigrateService;
    private final TeacherDepartmentMigrateService teacherDepartmentMigrateService;

    //    @PostConstruct
    public void migrate() {
        long startTime = System.currentTimeMillis();

        Thread migrateThread = new Thread(() -> {
//            services.add(facultyMigrateService);
//            services.add(teacherDegreeMigrateService);
//            services.add(teacherMigrateService);
//            services.add(examTypeMigrateService);
//            services.add(citizenshipMigrateService);
//            services.add(studentLanguageMigrateService);
//            services.add(institutionMigrateService);
//            services.add(qualificationMigrateService);
//            services.add(hostelRoomMigrateService);
//            services.add(departmentMigrateService);
//            services.add(classroomsMigrateService);
//            services.add(disciplineMigrateService);
//            services.add(specialityMigrateService);
//            services.add(specializationMigrateService);
//            services.add(groupMigrateService);
            services.add(studentMigrateService);
            services.add(studyPlanMigrateService);
            services.add(absenceMigrateService);
            services.add(teacherDepartmentMigrateService);

            services.forEach(IMigrateExecutor::migrate);
        });

        migrateThread.setName("Migration Dean V1 to new version");
        migrateThread.start();
    }
}
