package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.services.students.InstitutionService;
import by.vstu.migrate.v1.models.students.V1InstitutionModel;
import by.vstu.migrate.v1.repo.V1InstitutionModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionMigrateService extends BaseMigrateService<InstitutionModel, V1InstitutionModel> {

    private final InstitutionService institutionService;
    private final V1InstitutionModelRepository v1InstitutionModelRepository;

    @Override
    public List<InstitutionModel> convertNotExistsFromDB() {
        return this.convertList(this.v1InstitutionModelRepository.findAll());
    }

    @Override
    public InstitutionModel convertSingle(V1InstitutionModel v1InstitutionModel, boolean update) {
        InstitutionModel i = new InstitutionModel();
        i.setId(v1InstitutionModel.getId());
        i.setSourceId(v1InstitutionModel.getSourceId());
        i.setStatus(v1InstitutionModel.getStatus().map());
        i.setCreated(v1InstitutionModel.getCreated());
        i.setUpdated(v1InstitutionModel.getUpdated());

        i.setFullName(v1InstitutionModel.getFullName());
        i.setShortName(v1InstitutionModel.getShortName());

        return i;
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
