package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import by.vstu.dean.old.models.DDisciplineModel;
import by.vstu.dean.old.repo.DDisciplineModelRepository;
import by.vstu.dean.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return this.convertList(this.dDisciplineModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public DisciplineModel convertSingle(DDisciplineModel dDisciplineModel, boolean update) {
        if (this.departments == null)
            this.departments = this.departmentModelRepository.findAll();

        DisciplineModel disciplineModel = new DisciplineModel();
        disciplineModel.setName(StringUtils.safeTrim(dDisciplineModel.getName()));
        disciplineModel.setShortName(StringUtils.safeTrim(dDisciplineModel.getShortName()));

        this.departments
                .stream()
                .filter(p ->
                        dDisciplineModel.getDkafId() != null
                                && p.getSourceId().equals(Long.valueOf(dDisciplineModel.getDkafId()))
                ).findAny().ifPresent(disciplineModel::setDepartment);
        disciplineModel.setStatus(EStatus.ACTIVE);
        disciplineModel.setSourceId(dDisciplineModel.getId());
        if (!update)
            disciplineModel.setCreated(LocalDateTime.now());
        disciplineModel.setUpdated(LocalDateTime.now());
        return disciplineModel;
    }

    public void fixIfNeeded(DisciplineModel disciplineModel, DepartmentModel departmentModel) {

        if (disciplineModel.getDepartment() == null) {
            disciplineModel.setDepartment(departmentModel);
            this.insertSingle(disciplineModel);
            return;
        }

        if (!disciplineModel.getDepartment().getId().equals(departmentModel.getId())) {
            disciplineModel.setDepartment(departmentModel);
            this.insertSingle(disciplineModel);
        }

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
