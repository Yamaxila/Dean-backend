package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.SpecializationModel;
import by.vstu.dean.future.repo.QualificationModelRepository;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.repo.DSpecializationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public SpecializationModel convertSingle(DSpecializationModel dSpecializationModel) {
        SpecializationModel specializationModel = new SpecializationModel();

        specializationModel.setSourceId(dSpecializationModel.getId());
        specializationModel.setStatus(EStatus.ACTIVE);

        specializationModel.setName(dSpecializationModel.getName());
        specializationModel.setSpezCode("<FIXME>");
        specializationModel.setShortName(dSpecializationModel.getShortName());

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
