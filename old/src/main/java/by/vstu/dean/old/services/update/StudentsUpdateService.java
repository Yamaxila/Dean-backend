package by.vstu.dean.old.services.update;

import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.old.services.migrate.StudentMigrateService;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.StudentService;
import org.springframework.stereotype.Service;


@Service
public class StudentsUpdateService extends BaseUpdateService<DStudentModel, DStudentModelRepository, StudentModel, StudentModelRepository, StudentService, StudentMigrateService> {

    public StudentsUpdateService(StudentModelRepository repo, DStudentModelRepository dRepo, StudentMigrateService baseMigrateService, StudentService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
