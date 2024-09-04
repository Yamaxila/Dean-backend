package by.vstu.dean.services.update;

import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.DSpecializationModel;
import by.vstu.dean.repo.DSpecializationModelRepository;
import by.vstu.dean.services.migrate.SpecializationMigrateService;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationUpdateService extends BaseUpdateService<DSpecializationModel, DSpecializationModelRepository, SpecializationModel, SpecializationModelRepository, SpecializationService, SpecializationMigrateService> {

    public SpecializationUpdateService(SpecializationModelRepository repo, DSpecializationModelRepository dRepo, SpecializationMigrateService baseMigrateService, SpecializationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
