package by.vstu.dean.services.updates;

import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.services.EducationService;
import by.vstu.dean.services.migrate.EducationMigrateService;
import org.springframework.stereotype.Service;

@Service
public class EducationUpdateService extends BaseUpdateService<DStudentModel, DStudentModelRepository, EducationModel, EducationModelRepository, EducationService, EducationMigrateService>{

    public EducationUpdateService(EducationModelRepository repo, DStudentModelRepository dRepo, EducationMigrateService baseMigrateService, EducationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public void update() {
        System.err.println("DISABLED " + this.getClass().getName());
    }

}
