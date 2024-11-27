package by.vstu.old.dean.services.update;

import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import by.vstu.old.dean.models.DSpecialityModel;
import by.vstu.old.dean.repo.DSpecialityModelRepository;
import by.vstu.old.dean.services.migrate.SpecialityMigrateService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityUpdateService extends BaseUpdateService<DSpecialityModel, DSpecialityModelRepository, SpecialityModel, SpecialityModelRepository, SpecialityService, SpecialityMigrateService> {
    public SpecialityUpdateService(SpecialityModelRepository repo, DSpecialityModelRepository dRepo, SpecialityMigrateService baseMigrateService, SpecialityService service) {
        super(repo, dRepo, baseMigrateService, service);
    }
}
