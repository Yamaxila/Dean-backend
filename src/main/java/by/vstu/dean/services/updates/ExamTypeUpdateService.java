package by.vstu.dean.services.updates;

import by.vstu.dean.future.models.lessons.ExamModel;
import by.vstu.dean.future.repo.ExamModelRepository;
import by.vstu.dean.old.models.DExamModel;
import by.vstu.dean.old.repo.DExamModelModelRepository;
import by.vstu.dean.services.ExamTypeService;
import by.vstu.dean.services.migrate.ExamTypeMigrateService;
import org.springframework.stereotype.Service;

@Service
public class ExamTypeUpdateService extends BaseUpdateService<DExamModel, DExamModelModelRepository, ExamModel, ExamModelRepository, ExamTypeService, ExamTypeMigrateService> {

    public ExamTypeUpdateService(ExamModelRepository repo, DExamModelModelRepository dRepo, ExamTypeMigrateService baseMigrateService, ExamTypeService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
