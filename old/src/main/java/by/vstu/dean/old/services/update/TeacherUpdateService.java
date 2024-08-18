package by.vstu.dean.old.services.update;

import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.repo.TeacherModelRepository;
import by.vstu.dean.old.models.DTeacherModel;
import by.vstu.dean.old.repo.DTeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import by.vstu.dean.old.services.migrate.TeacherMigrateService;
import org.springframework.stereotype.Service;

@Service
public class TeacherUpdateService extends BaseUpdateService<TeacherDTO, DTeacherModel, DTeacherModelRepository, TeacherModel, TeacherMapper, TeacherModelRepository, TeacherService, TeacherMigrateService> {
    public TeacherUpdateService(TeacherModelRepository repo, DTeacherModelRepository dRepo, TeacherMigrateService baseMigrateService, TeacherService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
