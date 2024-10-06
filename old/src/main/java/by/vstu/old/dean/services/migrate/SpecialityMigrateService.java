package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.services.SpecialityService;
import by.vstu.old.dean.models.DSpecialityModel;
import by.vstu.old.dean.models.DSpecializationModel;
import by.vstu.old.dean.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecialityMigrateService extends BaseMigrateService<SpecialityModel, DSpecialityModel> {

    private final SpecialityService specialityService;
    private final DStudentModelRepository dStudentRepo;

    @Override
    public Long getLastDBId() {
        return this.specialityService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.specialityService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<SpecialityModel> convertNotExistsFromDB() {

        List<Long> ids = this.specialityService.getAll().stream().map(DBBaseModel::getSourceId).distinct().toList();
        List<SpecialityModel> out = new ArrayList<>();

        this.dStudentRepo.findAllSpecializations(14000L).stream()
                .filter(p -> p.getSpeciality() != null)
                .map(DSpecializationModel::getSpeciality).distinct()
                .filter(p -> !ids.contains(p.getId())).forEach(spec -> out.add(this.convertSingle(spec)));

        return out;
    }

    @Override
    public SpecialityModel convertSingle(DSpecialityModel spec, boolean update) {

        SpecialityModel specialityModel = new SpecialityModel();
        specialityModel.setName(StringUtils.safeTrim(spec.getName()));
        specialityModel.setShortName(StringUtils.safeTrim(spec.getShort_name()));
        specialityModel.setSpecCode(StringUtils.safeTrim(spec.getSpecCode()));
        specialityModel.setStatus(EStatus.ACTIVE);
        specialityModel.setSourceId(spec.getId());

        if (!update)
            specialityModel.setCreated(LocalDateTime.now());
        specialityModel.setUpdated(LocalDateTime.now());

        return specialityModel;
    }

    @Override
    public List<SpecialityModel> convertList(List<DSpecialityModel> t) {
        List<SpecialityModel> out = new ArrayList<>();

        t.forEach(spec -> out.add(this.convertSingle(spec)));

        return out;
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
