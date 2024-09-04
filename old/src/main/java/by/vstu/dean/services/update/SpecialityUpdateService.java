package by.vstu.dean.services.update;

import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.models.DSpecialityModel;
import by.vstu.dean.repo.DSpecialityModelRepository;
import by.vstu.dean.services.migrate.SpecialityMigrateService;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityUpdateService extends BaseUpdateService<DSpecialityModel, DSpecialityModelRepository, SpecialityModel, SpecialityModelRepository, SpecialityService, SpecialityMigrateService> {
    public SpecialityUpdateService(SpecialityModelRepository repo, DSpecialityModelRepository dRepo, SpecialityMigrateService baseMigrateService, SpecialityService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
