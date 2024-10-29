package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.services.students.InstitutionService;
import by.vstu.old.dean.models.DInstitutionModel;
import by.vstu.old.dean.repo.DInstitutionModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionMigrateService extends BaseMigrateService<InstitutionModel, DInstitutionModel> {

    private final DInstitutionModelRepository dInstitutionRepository;
    private final InstitutionService institutionService;

    @Override
    public Long getLastDBId() {
        return this.institutionService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.institutionService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<InstitutionModel> convertNotExistsFromDB() {
        return this.convertList(this.dInstitutionRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public InstitutionModel convertSingle(DInstitutionModel dInstitutionModel, boolean update) {
        InstitutionModel institutionModel = new InstitutionModel(dInstitutionModel.getFullName(), dInstitutionModel.getShortName());
        institutionModel.setStatus(dInstitutionModel.getStatus() != null && dInstitutionModel.getStatus() == 1 ? EStatus.ACTIVE : EStatus.DELETED);
        institutionModel.setSourceId(dInstitutionModel.getId());
        if (!update)
            institutionModel.setCreated(LocalDateTime.now());
        institutionModel.setUpdated(LocalDateTime.now());
        return institutionModel;
    }

    @Override
    public List<InstitutionModel> convertList(List<DInstitutionModel> t) {
        List<InstitutionModel> out = new ArrayList<>();
        t.forEach(institution -> out.add(this.convertSingle(institution)));
        return out;
    }

    @Override
    public InstitutionModel insertSingle(InstitutionModel t) {
        return this.institutionService.save(t);
    }

    @Override
    public List<InstitutionModel> insertAll(List<InstitutionModel> t) {
        return this.institutionService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
