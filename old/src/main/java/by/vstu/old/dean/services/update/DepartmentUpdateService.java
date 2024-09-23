package by.vstu.old.dean.services.update;

import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.repo.DepartmentModelRepository;
import by.vstu.dean.services.DepartmentService;
import by.vstu.old.dean.models.DDepartmentModel;
import by.vstu.old.dean.repo.DDepartmentModelRepository;
import by.vstu.old.dean.services.migrate.DepartmentMigrateService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentUpdateService extends BaseUpdateService<DDepartmentModel, DDepartmentModelRepository, DepartmentModel, DepartmentModelRepository, DepartmentService, DepartmentMigrateService> {


    public DepartmentUpdateService(DepartmentModelRepository repo, DDepartmentModelRepository dRepo, DepartmentMigrateService baseMigrateService, DepartmentService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
