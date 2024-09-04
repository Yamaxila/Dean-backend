package by.vstu.dean.services.update;

import by.vstu.dean.models.students.EducationModel;
import by.vstu.dean.models.DStudentModel;
import by.vstu.dean.repo.DStudentModelRepository;
import by.vstu.dean.services.migrate.EducationMigrateService;
import by.vstu.dean.repo.EducationModelRepository;
import by.vstu.dean.services.EducationService;
import org.springframework.stereotype.Service;

@Service
public class EducationUpdateService extends BaseUpdateService<DStudentModel, DStudentModelRepository, EducationModel, EducationModelRepository, EducationService, EducationMigrateService> {

    public EducationUpdateService(EducationModelRepository repo, DStudentModelRepository dRepo, EducationMigrateService baseMigrateService, EducationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public void update() {
        System.err.println("DISABLED " + this.getClass().getName());
    }

}
