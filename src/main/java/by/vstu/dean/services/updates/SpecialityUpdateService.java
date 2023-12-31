package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.specs.SpecialityDTO;
import by.vstu.dean.dto.mapper.SpecialityMapper;
import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import by.vstu.dean.old.models.DSpecialityModel;
import by.vstu.dean.old.repo.DSpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import by.vstu.dean.services.migrate.SpecialityMigrateService;
import org.springframework.stereotype.Service;

@Service
public class SpecialityUpdateService extends BaseUpdateService<SpecialityDTO, DSpecialityModel, DSpecialityModelRepository, SpecialityModel, SpecialityMapper, SpecialityModelRepository, SpecialityService, SpecialityMigrateService> {
    public SpecialityUpdateService(SpecialityModelRepository repo, DSpecialityModelRepository dRepo, SpecialityMigrateService baseMigrateService, SpecialityService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
