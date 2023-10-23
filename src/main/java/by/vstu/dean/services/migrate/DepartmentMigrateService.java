package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import by.vstu.dean.future.repo.FacultyModelRepository;
import by.vstu.dean.old.models.DDepartmentModel;
import by.vstu.dean.old.repo.DDepartmentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentMigrateService extends BaseMigrateService<DepartmentModel, DDepartmentModel> {

    private final DDepartmentModelRepository dDepartmentModelRepository;
    private final DepartmentModelRepository departmentModelRepository;
    private final FacultyModelRepository facultyModelRepository;

    private List<FacultyModel> facultyModels;

    @Override
    public Long getLastDBId() {
        return this.departmentModelRepository.findTopByOrderByIdDesc() == null ? 0 : this.departmentModelRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<DepartmentModel> convertNotExistsFromDB() {
        return this.convertList(this.dDepartmentModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public DepartmentModel convertSingle(DDepartmentModel dDepartmentModel, boolean update) {
        if (this.facultyModels == null)
            this.facultyModels = this.facultyModelRepository.findAll();
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setName(dDepartmentModel.getName());
        departmentModel.setShortName(dDepartmentModel.getShortName());
        departmentModel.setRoom(dDepartmentModel.getRoom());
        this.facultyModels.stream().filter(p -> p.getSourceId().equals(dDepartmentModel.getFaculty().getId())).findAny().ifPresent(departmentModel::setFaculty);
        departmentModel.setStatus(EStatus.ACTIVE);
        departmentModel.setSourceId(dDepartmentModel.getId());
        if (!update)
            departmentModel.setCreated(LocalDateTime.now());
        departmentModel.setUpdated(LocalDateTime.now());
        return departmentModel;
    }

    @Override
    public List<DepartmentModel> convertList(List<DDepartmentModel> t) {
        List<DepartmentModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    @Override
    public DepartmentModel insertSingle(DepartmentModel t) {
        return this.departmentModelRepository.saveAndFlush(t);
    }

    @Override
    public List<DepartmentModel> insertAll(List<DepartmentModel> t) {
        return this.departmentModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.facultyModels = this.facultyModelRepository.findAll();
        this.insertAll(this.convertNotExistsFromDB());
    }
}
