package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.students.internal.StudentLanguageModel;
import by.vstu.dean.services.students.StudentLanguageService;
import by.vstu.migrate.v1.models.students.V1StudentLanguageModel;
import by.vstu.migrate.v1.repo.V1StudentLanguageModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLanguageMigrateService extends BaseMigrateService<StudentLanguageModel, V1StudentLanguageModel> {

    private final StudentLanguageService studentLanguageService;
    private final V1StudentLanguageModelRepository v1StudentLanguageModelRepository;

    @Override
    public List<StudentLanguageModel> convertNotExistsFromDB() {
        return this.convertList(this.v1StudentLanguageModelRepository.findAll());
    }

    @Override
    public StudentLanguageModel convertSingle(V1StudentLanguageModel v1StudentLanguageModel, boolean update) {
        StudentLanguageModel slm = new StudentLanguageModel();
        slm.setId(v1StudentLanguageModel.getId());
        slm.setSourceId(v1StudentLanguageModel.getSourceId());
        slm.setStatus(v1StudentLanguageModel.getStatus().map());
        slm.setCreated(v1StudentLanguageModel.getCreated());
        slm.setUpdated(v1StudentLanguageModel.getUpdated());

        slm.setName(v1StudentLanguageModel.getName());

        return slm;
    }

    @Override
    public StudentLanguageModel insertSingle(StudentLanguageModel t) {
        return this.studentLanguageService.save(t);
    }

    @Override
    public List<StudentLanguageModel> insertAll(List<StudentLanguageModel> t) {
        return this.studentLanguageService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
