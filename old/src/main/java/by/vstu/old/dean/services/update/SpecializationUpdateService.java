package by.vstu.old.dean.services.update;

import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import by.vstu.old.dean.models.DSpecializationModel;
import by.vstu.old.dean.repo.DSpecializationModelRepository;
import by.vstu.old.dean.services.migrate.SpecializationMigrateService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationUpdateService extends BaseUpdateService<DSpecializationModel, DSpecializationModelRepository, SpecializationModel, SpecializationModelRepository, SpecializationService, SpecializationMigrateService> {

    public SpecializationUpdateService(SpecializationModelRepository repo, DSpecializationModelRepository dRepo, SpecializationMigrateService baseMigrateService, SpecializationService service) {
        super(repo, dRepo, baseMigrateService, service);
    }
}
