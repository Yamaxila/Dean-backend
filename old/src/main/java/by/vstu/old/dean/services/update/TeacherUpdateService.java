package by.vstu.old.dean.services.update;

import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.repo.TeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import by.vstu.old.dean.models.DTeacherModel;
import by.vstu.old.dean.repo.DTeacherModelRepository;
import by.vstu.old.dean.services.migrate.TeacherMigrateService;
import org.springframework.stereotype.Service;

@Service
public class TeacherUpdateService extends BaseUpdateService<DTeacherModel, DTeacherModelRepository, TeacherModel, TeacherModelRepository, TeacherService, TeacherMigrateService> {
    public TeacherUpdateService(TeacherModelRepository repo, DTeacherModelRepository dRepo, TeacherMigrateService baseMigrateService, TeacherService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
