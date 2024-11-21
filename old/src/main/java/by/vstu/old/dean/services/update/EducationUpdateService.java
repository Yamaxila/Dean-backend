package by.vstu.old.dean.services.update;

import by.vstu.dean.models.students.internal.EducationModel;
import by.vstu.dean.repo.EducationModelRepository;
import by.vstu.dean.services.students.EducationService;
import by.vstu.old.dean.models.DStudentModel;
import by.vstu.old.dean.repo.DStudentModelRepository;
import by.vstu.old.dean.services.migrate.EducationMigrateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationUpdateService extends BaseUpdateService<DStudentModel, DStudentModelRepository, EducationModel, EducationModelRepository, EducationService, EducationMigrateService> {

    public EducationUpdateService(EducationModelRepository repo, DStudentModelRepository dRepo, EducationMigrateService baseMigrateService, EducationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public List<EducationModel> getUpdated() {
        return new ArrayList<>();
    }

    @Override
    public void update() {
        System.err.println("DISABLED " + this.getClass().getName());
    }

}
