package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.FacultyService;
import by.vstu.migrate.v1.models.lessons.V1DepartmentModel;
import by.vstu.migrate.v1.repo.V1DepartmentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentMigrateService extends BaseMigrateService<DepartmentModel, V1DepartmentModel> {

    private final DepartmentService departmentService;
    private final V1DepartmentModelRepository v1DepartmentModelRepository;

    private final FacultyService facultyService;

    @Override
    public List<DepartmentModel> convertNotExistsFromDB() {
        return this.convertList(this.v1DepartmentModelRepository.findAll());
    }

    @Override
    public DepartmentModel convertSingle(V1DepartmentModel v1DepartmentModel, boolean update) {
        DepartmentModel d = new DepartmentModel();
        d.setId(v1DepartmentModel.getId());
        d.setSourceId(v1DepartmentModel.getSourceId());
        d.setStatus(v1DepartmentModel.getStatus().map());
        d.setCreated(v1DepartmentModel.getCreated());
        d.setUpdated(v1DepartmentModel.getUpdated());

        d.setName(v1DepartmentModel.getName());
        d.setShortName(v1DepartmentModel.getShortName());
        d.setRoom(v1DepartmentModel.getRoom());

        this.facultyService.getById(v1DepartmentModel.getFaculty().getId()).ifPresent(d::setFaculty);

        if (d.getFaculty() == null) {
            throw new RuntimeException("Faculty for department with id = %d not found".formatted(v1DepartmentModel.getId()));
        }

        return d;
    }

    @Override
    public DepartmentModel insertSingle(DepartmentModel t) {
        return this.departmentService.save(t);
    }

    @Override
    public List<DepartmentModel> insertAll(List<DepartmentModel> t) {
        return this.departmentService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
