package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.services.QualificationService;
import by.vstu.migrate.v1.models.specs.V1QualificationModel;
import by.vstu.migrate.v1.repo.V1QualificationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationMigrateService extends BaseMigrateService<QualificationModel, V1QualificationModel> {

    private final QualificationService qualificationService;
    private final V1QualificationModelRepository v1QualificationModelRepository;

    @Override
    public List<QualificationModel> convertNotExistsFromDB() {
        return this.convertList(this.v1QualificationModelRepository.findAll());
    }

    @Override
    public QualificationModel convertSingle(V1QualificationModel v1QualificationModel, boolean update) {
        QualificationModel q = new QualificationModel();
        q.setId(v1QualificationModel.getId());
        q.setSourceId(v1QualificationModel.getSourceId());
        q.setStatus(v1QualificationModel.getStatus().map());
        q.setCreated(v1QualificationModel.getCreated());
        q.setUpdated(v1QualificationModel.getUpdated());

        q.setName(v1QualificationModel.getName());
        q.setNameGenitive(v1QualificationModel.getNameGenitive());

        return q;
    }

    @Override
    public QualificationModel insertSingle(QualificationModel t) {
        return this.qualificationService.save(t);
    }

    @Override
    public List<QualificationModel> insertAll(List<QualificationModel> t) {
        return this.qualificationService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
