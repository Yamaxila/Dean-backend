package by.vstu.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.DSpecializationModel;
import by.vstu.dean.repo.DSpecializationModelRepository;
import by.vstu.dean.repo.QualificationModelRepository;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.repo.SpecializationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationMigrateService extends BaseMigrateService<SpecializationModel, DSpecializationModel> {

    private final SpecialityModelRepository specialityRepository;
    private final SpecializationModelRepository specializationRepository;
    private final QualificationModelRepository qualificationModelRepository;

    private final DSpecializationModelRepository dSpecializationRepository;

    @Override
    public Long getLastDBId() {
        return specializationRepository.findTopByOrderByIdDesc() == null ? 0 : this.specializationRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<SpecializationModel> convertNotExistsFromDB() {

        List<DSpecializationModel> temp = new ArrayList<>();
        List<Long> ids = this.specialityRepository.findAll().stream().map(DBBaseModel::getSourceId).distinct().toList();
        List<Long> spezIds = this.specializationRepository.findAll().stream().map(DBBaseModel::getSourceId).distinct().toList();

        this.dSpecializationRepository.findAll()
                .stream()
                .filter(p -> !spezIds.contains(p.getId()))
                .filter(p -> p.getSpeciality() != null && ids.contains(p.getSpeciality().getId()))
                .forEach(temp::add);

        return this.convertList(temp);
    }

    @Override
    public SpecializationModel convertSingle(DSpecializationModel dSpecializationModel, boolean update) {
        SpecializationModel specializationModel = new SpecializationModel();

        specializationModel.setSourceId(dSpecializationModel.getId());
        specializationModel.setStatus(EStatus.ACTIVE);
        if (!update)
            specializationModel.setCreated(LocalDateTime.now());
        specializationModel.setUpdated(LocalDateTime.now());

        specializationModel.setName(StringUtils.safeTrim(dSpecializationModel.getName()));
        specializationModel.setSpezCode("<FIXME>");
        specializationModel.setShortName(StringUtils.safeTrim(dSpecializationModel.getShortName()));

        specializationModel.setQualification(this.qualificationModelRepository.findBySourceId(dSpecializationModel.getDkvalifId() == null ? 1 : dSpecializationModel.getDkvalifId().getId()));
        specializationModel.setSpec(this.specialityRepository.findBySourceId(dSpecializationModel.getSpeciality() == null ? 319 : dSpecializationModel.getSpeciality().getId()));

        return specializationModel;
    }

    @Override
    public List<SpecializationModel> convertList(List<DSpecializationModel> t) {
        List<SpecializationModel> out = new ArrayList<>();

        t.forEach(spez -> out.add(this.convertSingle(spez)));

        return out;
    }

    @Override
    public SpecializationModel insertSingle(SpecializationModel t) {
        return this.specializationRepository.saveAndFlush(t);
    }

    @Override
    public List<SpecializationModel> insertAll(List<SpecializationModel> t) {
        return this.specializationRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
