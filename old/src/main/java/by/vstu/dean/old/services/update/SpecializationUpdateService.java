package by.vstu.dean.old.services.update;

import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.repo.DSpecializationModelRepository;
import by.vstu.dean.old.services.migrate.SpecializationMigrateService;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationUpdateService extends BaseUpdateService<DSpecializationModel, DSpecializationModelRepository, SpecializationModel, SpecializationModelRepository, SpecializationService, SpecializationMigrateService> {

    public SpecializationUpdateService(SpecializationModelRepository repo, DSpecializationModelRepository dRepo, SpecializationMigrateService baseMigrateService, SpecializationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
