package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.lessons.TeacherDTO;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.repo.TeacherModelRepository;
import by.vstu.dean.old.models.DTeacherModel;
import by.vstu.dean.old.repo.DTeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import by.vstu.dean.services.migrate.TeacherMigrateService;
import org.springframework.stereotype.Service;

@Service
public class TeacherUpdateService extends BaseUpdateService<TeacherDTO, DTeacherModel, DTeacherModelRepository, TeacherModel, TeacherMapper, TeacherModelRepository, TeacherService, TeacherMigrateService> {
    public TeacherUpdateService(TeacherModelRepository repo, DTeacherModelRepository dRepo, TeacherMigrateService baseMigrateService, TeacherService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
