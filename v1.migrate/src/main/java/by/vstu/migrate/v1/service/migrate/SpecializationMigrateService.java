package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.services.QualificationService;
import by.vstu.dean.services.SpecialityService;
import by.vstu.dean.services.SpecializationService;
import by.vstu.migrate.v1.models.specs.V1SpecializationModel;
import by.vstu.migrate.v1.repo.V1SpecializationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationMigrateService extends BaseMigrateService<SpecializationModel, V1SpecializationModel> {

    private final SpecializationService specializationService;
    private final V1SpecializationModelRepository v1SpecializationModelRepository;

    private final SpecialityService specialityService;
    private final QualificationService qualificationService;

    @Override
    public List<SpecializationModel> convertNotExistsFromDB() {
        return this.convertList(this.v1SpecializationModelRepository.findAll());
    }

    @Override
    public SpecializationModel convertSingle(V1SpecializationModel v1SpecializationModel, boolean update) {
        SpecializationModel s = new SpecializationModel();
        s.setId(v1SpecializationModel.getId());
        s.setSourceId(v1SpecializationModel.getSourceId());
        s.setStatus(v1SpecializationModel.getStatus().map());
        s.setCreated(v1SpecializationModel.getCreated());
        s.setUpdated(v1SpecializationModel.getUpdated());

        s.setName(v1SpecializationModel.getName());
        s.setShortName(v1SpecializationModel.getShortName());
        s.setSpezCode(v1SpecializationModel.getSpezCode());

        this.specialityService.getById(v1SpecializationModel.getSpec().getId()).ifPresent(s::setSpec);

        if (s.getSpec() == null) {
            throw new RuntimeException("Spec for specialization with id = %d not found".formatted(v1SpecializationModel.getId()));
        }

        this.qualificationService.getById(v1SpecializationModel.getQualification().getId()).ifPresent(s::setQualification);

        if (s.getQualification() == null) {
            throw new RuntimeException("Qualification for specialization with id = %d not found".formatted(v1SpecializationModel.getId()));
        }

        return s;
    }

    @Override
    public SpecializationModel insertSingle(SpecializationModel t) {
        return this.specializationService.save(t);
    }

    @Override
    public List<SpecializationModel> insertAll(List<SpecializationModel> t) {
        return this.specializationService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
