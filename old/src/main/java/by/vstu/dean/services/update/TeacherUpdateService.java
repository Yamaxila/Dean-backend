package by.vstu.dean.services.update;

import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.DTeacherModel;
import by.vstu.dean.repo.DTeacherModelRepository;
import by.vstu.dean.services.migrate.TeacherMigrateService;
import by.vstu.dean.repo.TeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherUpdateService extends BaseUpdateService<DTeacherModel, DTeacherModelRepository, TeacherModel, TeacherModelRepository, TeacherService, TeacherMigrateService> {
    public TeacherUpdateService(TeacherModelRepository repo, DTeacherModelRepository dRepo, TeacherMigrateService baseMigrateService, TeacherService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
