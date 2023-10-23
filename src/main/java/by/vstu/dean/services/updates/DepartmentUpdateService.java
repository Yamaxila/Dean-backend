package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.lessons.DepartmentDTO;
import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import by.vstu.dean.old.models.DDepartmentModel;
import by.vstu.dean.old.repo.DDepartmentModelRepository;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.migrate.DepartmentMigrateService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentUpdateService extends BaseUpdateService<DepartmentDTO, DDepartmentModel, DDepartmentModelRepository, DepartmentModel, DepartmentMapper, DepartmentModelRepository, DepartmentService, DepartmentMigrateService> {


    public DepartmentUpdateService(DepartmentModelRepository repo, DDepartmentModelRepository dRepo, DepartmentMigrateService baseMigrateService, DepartmentService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
