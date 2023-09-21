package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.InstitutionModel;
import by.vstu.dean.future.repo.InstitutionModelRepository;
import by.vstu.dean.old.OldDBBaseModel;
import by.vstu.dean.old.models.DInstitutionModel;
import by.vstu.dean.old.repo.DInstitutionModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionMigrateService extends BaseMigrateService<InstitutionModel, DInstitutionModel> {

    private final DInstitutionModelRepository dInstitutionRepository;
    private final InstitutionModelRepository institutionRepository;

    @Override
    public Long getLastDBId() {
        return this.institutionRepository.findTopByOrderByIdDesc() == null ? 0 : this.institutionRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<InstitutionModel> convertNotExistsFromDB() {
        List<OldDBBaseModel> bases = this.dInstitutionRepository.findAllByIdAfter(this.getLastDBId());
        List<DInstitutionModel> temp = new ArrayList<>();

        bases.forEach(base -> temp.add((DInstitutionModel)base));

        return this.convertList(temp);
    }

    @Override
    public InstitutionModel convertSingle(DInstitutionModel dInstitutionModel) {
        InstitutionModel institutionModel = new InstitutionModel(dInstitutionModel.getFullName(), dInstitutionModel.getShortName());
        institutionModel.setStatus(dInstitutionModel.getStatus() != null && dInstitutionModel.getStatus() == 1 ? EStatus.ACTIVE : EStatus.DELETED);
        institutionModel.setSourceId(dInstitutionModel.getId());
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
        return this.institutionRepository.saveAndFlush(t);
    }

    @Override
    public List<InstitutionModel> insertAll(List<InstitutionModel> t) {
        return this.institutionRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
