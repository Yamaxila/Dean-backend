package by.vstu.dean.services.migrate;

import by.vstu.dean.future.models.SpecializationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainMigrateService {

    private List<IMigrateExecutor> services = new ArrayList<>();

    private final CitizenshipMigrateService citizenshipMigrateService;
    private final ForeignerMigrateService foreignerMigrateService;
    private final InstitutionMigrateService institutionMigrateService;
    private final QualificationMigrateService qualificationMigrateService;
    private final FacultyMigrateService facultyMigrateService;
    private final SpecialityMigrateService specialityMigrateService;
    private final SpecializationMigrateService specializationMigrateService;
    private final GroupMigrateService groupMigrateService;
    private final StudentMigrateService studentMigrateService;
    private final DocumentMigrateService documentMigrateService;

    @PostConstruct
    public void migrate() {
        Long startTime = System.currentTimeMillis();

        Thread migrateThread = new Thread(() -> {
            services.add(this.citizenshipMigrateService);
            services.add(this.foreignerMigrateService);
            services.add(this.institutionMigrateService);
            services.add(this.qualificationMigrateService);
            services.add(this.facultyMigrateService);
            services.add(this.specialityMigrateService);
            services.add(this.specializationMigrateService);
            services.add(this.groupMigrateService);
            services.add(this.studentMigrateService);
//            services.add(this.documentMigrateService);

            services.forEach(IMigrateExecutor::migrate);

        });


        Thread checkThread = new Thread(() -> {
            boolean check = false;
            while(Thread.getAllStackTraces().keySet().stream().anyMatch(p -> p.getName().contains("Migration"))) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {}

                if(System.currentTimeMillis() - startTime > 40*1000 && !check) {
                    System.out.println("I'm not dead yet! :)");
                    check = true;
                }
            }

            System.err.println("Migration time(s): " + Math.floor(((double)System.currentTimeMillis() - (double)startTime)/1000D));

        });

        migrateThread.setName("Migration");
        migrateThread.start();

        checkThread.setName("checkThread");
        checkThread.start();

    }

}
