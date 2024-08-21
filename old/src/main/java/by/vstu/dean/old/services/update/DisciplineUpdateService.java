package by.vstu.dean.old.services.update;

import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.old.models.DDisciplineModel;
import by.vstu.dean.old.repo.DDisciplineModelRepository;
import by.vstu.dean.old.services.migrate.DisciplineMigrateService;
import by.vstu.dean.repo.DisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import org.springframework.stereotype.Service;

@Service
public class DisciplineUpdateService extends BaseUpdateService<DDisciplineModel, DDisciplineModelRepository, DisciplineModel, DisciplineModelRepository, DisciplineService, DisciplineMigrateService> {

    public DisciplineUpdateService(DisciplineModelRepository repo, DDisciplineModelRepository dRepo, DisciplineMigrateService baseMigrateService, DisciplineService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public void update() {
        System.err.println("DISABLED " + this.getClass().getName());
    }
}
