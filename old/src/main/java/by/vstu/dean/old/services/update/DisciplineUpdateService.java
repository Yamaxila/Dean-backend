package by.vstu.dean.old.services.update;

import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.dto.mapper.DisciplineMapper;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import by.vstu.dean.old.models.DDisciplineModel;
import by.vstu.dean.old.repo.DDisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import by.vstu.dean.old.services.migrate.DisciplineMigrateService;
import org.springframework.stereotype.Service;

@Service
public class DisciplineUpdateService extends BaseUpdateService<DisciplineDTO, DDisciplineModel, DDisciplineModelRepository, DisciplineModel, DisciplineMapper, DisciplineModelRepository, DisciplineService, DisciplineMigrateService> {

    public DisciplineUpdateService(DisciplineModelRepository repo, DDisciplineModelRepository dRepo, DisciplineMigrateService baseMigrateService, DisciplineService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public void update() {

    }
}
