package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.students.CitizenshipModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import by.vstu.dean.old.models.DCitizenshipModel;
import by.vstu.dean.old.repo.DCitizenshipModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitizenshipMigrateService extends BaseMigrateService<CitizenshipModel, DCitizenshipModel> {

    private final DCitizenshipModelRepository dCitizenshipRepository;
    private final CitizenshipModelRepository citizenshipRepository;

    @Override
    public Long getLastDBId() {
        return this.citizenshipRepository.findTopByOrderByIdDesc() == null ? 0 : this.citizenshipRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<CitizenshipModel> convertNotExistsFromDB() {
        return this.convertList(this.dCitizenshipRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public CitizenshipModel convertSingle(DCitizenshipModel dCitizenshipModel, boolean update) {
        CitizenshipModel citizenshipModel = new CitizenshipModel(dCitizenshipModel.getName());
        citizenshipModel.setStatus(EStatus.ACTIVE);
        citizenshipModel.setSourceId(dCitizenshipModel.getId());
        if(!update)
            citizenshipModel.setCreated(LocalDateTime.now());
        citizenshipModel.setUpdated(LocalDateTime.now());
        return citizenshipModel;
    }

    @Override
    public List<CitizenshipModel> convertList(List<DCitizenshipModel> t) {
        List<CitizenshipModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    @Override
    public CitizenshipModel insertSingle(CitizenshipModel t) {
        return this.citizenshipRepository.saveAndFlush(t);
    }

    @Override
    public List<CitizenshipModel> insertAll(List<CitizenshipModel> t) {
        return this.citizenshipRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
