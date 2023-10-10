package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import by.vstu.dean.old.models.DSpecialityModel;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.repo.DSpecialityModelRepository;
import by.vstu.dean.old.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecialityMigrateService extends BaseMigrateService<SpecialityModel, DSpecialityModel> {

    private final DSpecialityModelRepository dSpecialityRepo;
    private final SpecialityModelRepository specialityRepo;
    private final DStudentModelRepository dStudentRepo;

    @Override
    public Long getLastDBId() {
        return this.specialityRepo.findTopByOrderByIdDesc() == null ? 0 : this.specialityRepo.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<SpecialityModel> convertNotExistsFromDB() {

        List<Long> ids = this.specialityRepo.findAll().stream().map(DBBaseModel::getSourceId).distinct().toList();
        List<SpecialityModel> out = new ArrayList<>();

        this.dStudentRepo.findAllSpecializations(14000L).stream()
                .filter(p -> p.getSpeciality() != null)
                .map(DSpecializationModel::getSpeciality).distinct()
                .filter(p -> !ids.contains(p.getId())).forEach(spec -> {
                    out.add(this.convertSingle(spec));
                });

        return out;
    }

    @Override
    public SpecialityModel convertSingle(DSpecialityModel spec) {

        SpecialityModel specialityModel = new SpecialityModel();
        specialityModel.setName(spec.getName());
        specialityModel.setShortName(spec.getShort_name());
        specialityModel.setSpecCode(spec.getSpecCode());
        specialityModel.setStatus(EStatus.ACTIVE);
        specialityModel.setSourceId(spec.getId());

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
        return this.specialityRepo.saveAndFlush(t);
    }

    @Override
    public List<SpecialityModel> insertAll(List<SpecialityModel> t) {
        return this.specialityRepo.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
