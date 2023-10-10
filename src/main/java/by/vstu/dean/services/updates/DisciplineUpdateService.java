package by.vstu.dean.services.updates;

import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import by.vstu.dean.old.models.DDisciplineModel;
import by.vstu.dean.old.repo.DDisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import by.vstu.dean.services.migrate.DisciplineMigrateService;
import org.springframework.stereotype.Service;

@Service
public class DisciplineUpdateService extends BaseUpdateService<DDisciplineModel, DDisciplineModelRepository, DisciplineModel, DisciplineModelRepository, DisciplineService, DisciplineMigrateService> {

    public DisciplineUpdateService(DisciplineModelRepository repo, DDisciplineModelRepository dRepo, DisciplineMigrateService baseMigrateService, DisciplineService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
