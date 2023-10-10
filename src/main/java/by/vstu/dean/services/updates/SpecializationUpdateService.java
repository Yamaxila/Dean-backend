package by.vstu.dean.services.updates;

import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.repo.DSpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import by.vstu.dean.services.migrate.SpecializationMigrateService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationUpdateService extends BaseUpdateService<DSpecializationModel, DSpecializationModelRepository, SpecializationModel, SpecializationModelRepository, SpecializationService, SpecializationMigrateService> {

    public SpecializationUpdateService(SpecializationModelRepository repo, DSpecializationModelRepository dRepo, SpecializationMigrateService baseMigrateService, SpecializationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
