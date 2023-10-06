package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import by.vstu.dean.old.OldDBBaseModel;
import by.vstu.dean.old.models.DDisciplineModel;
import by.vstu.dean.old.repo.DDisciplineModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineMigrateService extends BaseMigrateService<DisciplineModel, DDisciplineModel> {

    private final DDisciplineModelRepository dDisciplineModelRepository;
    private final DisciplineModelRepository disciplineModelRepository;
    private final DepartmentModelRepository departmentModelRepository;

    private List<DepartmentModel> departments;

    @Override
    public Long getLastDBId() {
        return this.disciplineModelRepository.findTopByOrderByIdDesc() == null ? 0 : this.disciplineModelRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<DisciplineModel> convertNotExistsFromDB() {

        List<OldDBBaseModel> bases = this.dDisciplineModelRepository.findAllByIdAfter(this.getLastDBId());
        List<DDisciplineModel> temp = new ArrayList<>();

        bases.forEach(base -> temp.add((DDisciplineModel) base));

        return this.convertList(temp);
    }

    @Override
    public DisciplineModel convertSingle(DDisciplineModel dDisciplineModel) {
        DisciplineModel disciplineModel = new DisciplineModel();
        disciplineModel.setName(dDisciplineModel.getName());
        disciplineModel.setShortName(dDisciplineModel.getShortName());

        DepartmentModel department = this.departments
                .stream()
                .filter(p ->
                        dDisciplineModel.getDkafId() != null
                                && p.getSourceId().equals(Long.valueOf(dDisciplineModel.getDkafId()))
                ).findAny().orElse(null);
        if (department != null)
            disciplineModel.setDepartment(department);
        disciplineModel.setStatus(EStatus.ACTIVE);
        disciplineModel.setSourceId(dDisciplineModel.getId());
        return disciplineModel;
    }

    @Override
    public List<DisciplineModel> convertList(List<DDisciplineModel> t) {
        List<DisciplineModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    @Override
    public DisciplineModel insertSingle(DisciplineModel t) {
        return this.disciplineModelRepository.saveAndFlush(t);
    }

    @Override
    public List<DisciplineModel> insertAll(List<DisciplineModel> t) {
        return this.disciplineModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.departments = this.departmentModelRepository.findAll();
        this.insertAll(this.convertNotExistsFromDB());
    }
}
