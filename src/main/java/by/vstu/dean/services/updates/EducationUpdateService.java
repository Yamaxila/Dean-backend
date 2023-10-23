package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.students.EducationDTO;
import by.vstu.dean.dto.mapper.EducationMapper;
import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.services.EducationService;
import by.vstu.dean.services.migrate.EducationMigrateService;
import org.springframework.stereotype.Service;

@Service
public class EducationUpdateService extends BaseUpdateService<EducationDTO, DStudentModel, DStudentModelRepository, EducationModel, EducationMapper, EducationModelRepository, EducationService, EducationMigrateService>{

    public EducationUpdateService(EducationModelRepository repo, DStudentModelRepository dRepo, EducationMigrateService baseMigrateService, EducationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public void update() {
        System.err.println("DISABLED " + this.getClass().getName());
    }

}
