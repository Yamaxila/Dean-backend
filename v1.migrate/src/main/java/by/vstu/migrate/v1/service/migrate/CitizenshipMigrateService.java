package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.services.students.CitizenshipService;
import by.vstu.migrate.v1.models.students.V1CitizenshipModel;
import by.vstu.migrate.v1.repo.V1CitizenshipModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitizenshipMigrateService extends BaseMigrateService<CitizenshipModel, V1CitizenshipModel> {

    private final CitizenshipService citizenshipService;
    private final V1CitizenshipModelRepository v1CitizenshipModelRepository;

    @Override
    public List<CitizenshipModel> convertNotExistsFromDB() {
        return this.convertList(this.v1CitizenshipModelRepository.findAll());
    }

    @Override
    public CitizenshipModel convertSingle(V1CitizenshipModel v1CitizenshipModel, boolean update) {
        CitizenshipModel c = new CitizenshipModel();

        c.setId(v1CitizenshipModel.getId());
        c.setSourceId(v1CitizenshipModel.getSourceId());
        c.setStatus(v1CitizenshipModel.getStatus().map());
        c.setCreated(v1CitizenshipModel.getCreated());
        c.setUpdated(v1CitizenshipModel.getUpdated());

        c.setName(v1CitizenshipModel.getName());

        return c;
    }

    @Override
    public CitizenshipModel insertSingle(CitizenshipModel t) {
        return this.citizenshipService.save(t);
    }

    @Override
    public List<CitizenshipModel> insertAll(List<CitizenshipModel> t) {
        return this.citizenshipService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
