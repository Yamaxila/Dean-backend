package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.SpecialityService;
import by.vstu.migrate.v1.models.specs.V1SpecialityModel;
import by.vstu.migrate.v1.repo.V1SpecialityModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecialityMigrateService extends BaseMigrateService<SpecialityModel, V1SpecialityModel> {

    private final SpecialityService specialityService;
    private final V1SpecialityModelRepository v1SpecialityModelRepository;

    private final DepartmentService departmentService;

    @Override
    public List<SpecialityModel> convertNotExistsFromDB() {
        return this.convertList(this.v1SpecialityModelRepository.findAll());
    }

    @Override
    public SpecialityModel convertSingle(V1SpecialityModel v1SpecialityModel, boolean update) {
        SpecialityModel s = new SpecialityModel();
        s.setId(v1SpecialityModel.getId());
        s.setSourceId(v1SpecialityModel.getSourceId());
        s.setStatus(v1SpecialityModel.getStatus().map());
        s.setCreated(v1SpecialityModel.getCreated());
        s.setUpdated(v1SpecialityModel.getUpdated());

        s.setName(v1SpecialityModel.getName());
        s.setShortName(v1SpecialityModel.getShortName());
        s.setSpecCode(v1SpecialityModel.getSpecCode());

        if (v1SpecialityModel.getDepartment() != null)
            this.departmentService.getById(v1SpecialityModel.getDepartment().getId()).ifPresent(s::setDepartment);

        if (s.getDepartment() == null) {
            log.error("Department for speciality with id = {} not found", v1SpecialityModel.getId());
        }

        return s;
    }

    @Override
    public SpecialityModel insertSingle(SpecialityModel t) {
        return this.specialityService.save(t);
    }

    @Override
    public List<SpecialityModel> insertAll(List<SpecialityModel> t) {
        return this.specialityService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
