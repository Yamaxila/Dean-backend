package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.services.ExamTypeService;
import by.vstu.migrate.v1.models.lessons.V1ExamModel;
import by.vstu.migrate.v1.repo.V1ExamModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamTypeMigrateService extends BaseMigrateService<ExamModel, V1ExamModel> {

    private final ExamTypeService examTypeService;
    private final V1ExamModelRepository v1ExamModelRepository;

    @Override
    public List<ExamModel> convertNotExistsFromDB() {
        return this.convertList(this.v1ExamModelRepository.findAll());
    }

    @Override
    public ExamModel convertSingle(V1ExamModel v1ExamModel, boolean update) {
        ExamModel e = new ExamModel();
        e.setId(v1ExamModel.getId());
        e.setSourceId(v1ExamModel.getSourceId());
        e.setStatus(v1ExamModel.getStatus().map());
        e.setCreated(v1ExamModel.getCreated());
        e.setUpdated(v1ExamModel.getUpdated());

        e.setName(v1ExamModel.getName());
        e.setType(v1ExamModel.getType().map());

        return e;
    }

    @Override
    public ExamModel insertSingle(ExamModel t) {
        return this.examTypeService.save(t);
    }

    @Override
    public List<ExamModel> insertAll(List<ExamModel> t) {
        return this.examTypeService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
