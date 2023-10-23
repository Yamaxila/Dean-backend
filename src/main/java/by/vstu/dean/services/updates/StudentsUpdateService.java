package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.students.StudentDTO;
import by.vstu.dean.dto.mapper.StudentMapper;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.services.StudentService;
import by.vstu.dean.services.migrate.StudentMigrateService;
import org.springframework.stereotype.Service;


@Service
public class StudentsUpdateService extends BaseUpdateService<StudentDTO, DStudentModel, DStudentModelRepository, StudentModel, StudentMapper, StudentModelRepository, StudentService, StudentMigrateService> {

    public StudentsUpdateService(StudentModelRepository repo, DStudentModelRepository dRepo, StudentMigrateService baseMigrateService, StudentService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
