package by.vstu.old.dean.services.update;

import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import by.vstu.dean.services.ExamTypeService;
import by.vstu.old.dean.models.DExamModel;
import by.vstu.old.dean.repo.DExamModelModelRepository;
import by.vstu.old.dean.services.migrate.ExamTypeMigrateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamTypeUpdateService extends BaseUpdateService<DExamModel, DExamModelModelRepository, ExamModel, ExamModelRepository, ExamTypeService, ExamTypeMigrateService> {

    public ExamTypeUpdateService(ExamModelRepository repo, DExamModelModelRepository dRepo, ExamTypeMigrateService baseMigrateService, ExamTypeService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }

    @Override
    public List<ExamModel> getUpdated() {
        return new ArrayList<>();
    }

    //FIXME: Почему?
    @Override
    public void update() {
        System.err.println("DISABLED " + this.getClass().getName());
    }

}
