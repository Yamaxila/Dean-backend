package by.vstu.dean.old.services.update;

import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.dto.mapper.SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.repo.DSpecializationModelRepository;
import by.vstu.dean.old.services.migrate.SpecializationMigrateService;
import by.vstu.dean.services.SpecializationService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationUpdateService extends BaseUpdateService<SpecializationDTO, DSpecializationModel, DSpecializationModelRepository, SpecializationModel, SpecializationMapper, SpecializationModelRepository, SpecializationService, SpecializationMigrateService> {

    public SpecializationUpdateService(SpecializationModelRepository repo, DSpecializationModelRepository dRepo, SpecializationMigrateService baseMigrateService, SpecializationService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
