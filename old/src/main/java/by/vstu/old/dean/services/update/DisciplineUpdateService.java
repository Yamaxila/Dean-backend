package by.vstu.old.dean.services.update;

import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import by.vstu.old.dean.models.DDisciplineModel;
import by.vstu.old.dean.repo.DDisciplineModelRepository;
import by.vstu.old.dean.services.migrate.DisciplineMigrateService;
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
