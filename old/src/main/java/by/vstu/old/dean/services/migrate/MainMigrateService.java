package by.vstu.old.dean.services.migrate;

import by.vstu.old.dean.services.update.MainUpdateService;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainMigrateService {

    private List<IMigrateExecutor> services = new ArrayList<>();
    @Autowired
    private TeacherDegreeMigrateService teacherDegreeMigrateService;
    @Autowired
    private TeacherMigrateService teacherMigrateService;
    @Autowired
    private ExamTypeMigrateService examTypeMigrateService;
    @Autowired
    private CitizenshipMigrateService citizenshipMigrateService;
    @Autowired
    private DepartmentMigrateService departmentMigrateService;
    @Autowired
    private DisciplineMigrateService disciplineMigrateService;
    @Autowired
    private StudentLanguageMigrateService studentLanguageMigrateService;
    @Autowired
    private InstitutionMigrateService institutionMigrateService;
    @Autowired
    private QualificationMigrateService qualificationMigrateService;
    @Autowired
    private FacultyMigrateService facultyMigrateService;
    @Autowired
    private SpecialityMigrateService specialityMigrateService;
    @Autowired
    private SpecializationMigrateService specializationMigrateService;
    @Autowired
    private GroupMigrateService groupMigrateService;
    @Autowired
    private StudentMigrateService studentMigrateService;
    @Autowired
    private StudyPlanMigrateService studyPlanMigrateService;
    @Autowired
    private EducationMigrateService educationMigrateService;
    @Autowired
    private DepartmentSpecialityMergeService departmentSpecialityMergeService;
    @Autowired
    private AbsenceMigrateService absenceMigrateService;
    @Autowired
    private TeacherDepartmentMigrateService teacherDepartmentMigrateService;
    @Autowired
    private StatementMigrateService statementMigrateService;
    @Autowired
    private MainUpdateService mainUpdateService;


    private DataSource deanDataSource;

    @Autowired
    public MainMigrateService(@Qualifier("deanDataSource") DataSource deanDataSource) {
        this.deanDataSource = deanDataSource;
    }

    @PostConstruct
    @Order
    public void migrate() {
        this.openDS();

        long startTime = System.currentTimeMillis();
        log.info("Migrate started at {}", startTime);
        services.clear();
//        services.add(this.teacherDegreeMigrateService);
//        services.add(this.teacherMigrateService);
//        services.add(this.examTypeMigrateService);
//        services.add(this.citizenshipMigrateService);
//        services.add(this.studentLanguageMigrateService);
//        services.add(this.institutionMigrateService);
//        services.add(this.qualificationMigrateService);
//        services.add(this.facultyMigrateService);
//        services.add(this.departmentMigrateService);
//        services.add(this.disciplineMigrateService);
//        services.add(this.specialityMigrateService);
//        services.add(this.specializationMigrateService);
//        services.add(this.groupMigrateService);
//        services.add(this.studentMigrateService);
//        services.add(this.studyPlanMigrateService);
//        services.add(this.absenceMigrateService);
//        services.add(this.departmentSpecialityMergeService);
//        services.add(this.teacherDepartmentMigrateService);
//        services.add(this.statementMigrateService);


        Thread migrateThread = new Thread(() -> {
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

            log.info("Cleanup...");
            services.forEach(IMigrateExecutor::cleanup);
            log.info("Migrate/Update done!");

            HikariDataSource hikariDs = (HikariDataSource) deanDataSource;
            HikariPoolMXBean poolBean = hikariDs.getHikariPoolMXBean();
            log.info("Current connections count is {}", poolBean.getActiveConnections());

//            this.closeDS();

            log.info("Current connections count after closing is {}", poolBean.getActiveConnections());
        });

        migrateThread.setName("Migration");
        migrateThread.start();

    }

    private void closeDS() {
        try {
            if (this.deanDataSource.getConnection() == null)
                throw new RuntimeException("Dean DS is null!");

            log.info("Closing dean dataSource");
            HikariDataSource hikariDs = (HikariDataSource) deanDataSource;
            HikariPoolMXBean poolBean = hikariDs.getHikariPoolMXBean();
            poolBean.suspendPool();
            log.info("Closed.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openDS() {
        try {
            if (this.deanDataSource.getConnection() == null)
                throw new RuntimeException("Dean DS is null!");

            log.info("Opening dean dataSource");

            if (!this.deanDataSource.getConnection().isClosed()) {
                log.info("Dean DS already connected!");
                return;
            }

            HikariDataSource hikariDs = (HikariDataSource) deanDataSource;
            HikariPoolMXBean poolBean = hikariDs.getHikariPoolMXBean();
            poolBean.resumePool();

            log.info("Resumed!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
