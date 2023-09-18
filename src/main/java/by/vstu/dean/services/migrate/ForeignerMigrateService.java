package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.CitizenshipModel;
import by.vstu.dean.future.models.ForeignerModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import by.vstu.dean.future.repo.ForeignerModelRepository;
import by.vstu.dean.old.OldDBBaseModel;
import by.vstu.dean.old.models.DForeignerModel;
import by.vstu.dean.old.repo.DForeignerModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForeignerMigrateService extends BaseMigrateService<ForeignerModel, DForeignerModel> {
    
    private final DForeignerModelRepository dForeignerRepository;
    private final ForeignerModelRepository foreignerRepository;

    @Override
    public Long getLastDBId() {
        return this.foreignerRepository.findTopByOrderByIdDesc() == null ? 0 : this.foreignerRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<ForeignerModel> convertNotExistsFromDB() {

        List<OldDBBaseModel> bases = this.dForeignerRepository.findAllByIdAfter(this.getLastDBId());
        List<DForeignerModel> temp = new ArrayList<>();

        bases.forEach(base -> temp.add((DForeignerModel)base));

        return this.convertList(temp);
    }

    @Override
    public ForeignerModel convertSingle(DForeignerModel dForeignerModel) {
        ForeignerModel foreignerModel = new ForeignerModel(dForeignerModel.getName());
        foreignerModel.setStatus(EStatus.ACTIVE);
        foreignerModel.setSourceId(dForeignerModel.getId());
        return foreignerModel;
    }

    @Override
    public List<ForeignerModel> convertList(List<DForeignerModel> t) {
        List<ForeignerModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    @Override
    public ForeignerModel insertSingle(ForeignerModel t) {
        return this.foreignerRepository.saveAndFlush(t);
    }

    @Override
    public List<ForeignerModel> insertAll(List<ForeignerModel> t) {
        return this.foreignerRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
