package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.DisciplineService;
import by.vstu.migrate.v1.models.lessons.V1DisciplineModel;
import by.vstu.migrate.v1.repo.V1DisciplineModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisciplineMigrateService extends BaseMigrateService<DisciplineModel, V1DisciplineModel> {

    private final DisciplineService disciplineService;
    private final V1DisciplineModelRepository v1DisciplineModelRepository;

    private final DepartmentService departmentService;

    @Override
    public List<DisciplineModel> convertNotExistsFromDB() {
        return this.convertList(this.v1DisciplineModelRepository.findAll());
    }

    @Override
    public DisciplineModel convertSingle(V1DisciplineModel v1DisciplineModel, boolean update) {
        DisciplineModel d = new DisciplineModel();
        d.setId(v1DisciplineModel.getId());
        d.setSourceId(v1DisciplineModel.getSourceId());
        d.setStatus(v1DisciplineModel.getStatus().map());
        d.setCreated(v1DisciplineModel.getCreated());
        d.setUpdated(v1DisciplineModel.getUpdated());

        d.setName(v1DisciplineModel.getName());
        d.setShortName(v1DisciplineModel.getShortName());

        if (v1DisciplineModel.getDepartment() != null)
            this.departmentService.getById(v1DisciplineModel.getDepartment().getId()).ifPresent(d::setDepartment);

        if (d.getDepartment() == null) {
            log.error("Department for discipline with id = {} not found", v1DisciplineModel.getId());
        }

        return d;
    }

    @Override
    public DisciplineModel insertSingle(DisciplineModel t) {
        return this.disciplineService.save(t);
    }

    @Override
    public List<DisciplineModel> insertAll(List<DisciplineModel> t) {
        return this.disciplineService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
