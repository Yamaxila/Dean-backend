package by.vstu.old.dean.services.update;

import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.students.StudentService;
import by.vstu.old.dean.models.DStudentModel;
import by.vstu.old.dean.repo.DStudentModelRepository;
import by.vstu.old.dean.services.migrate.StudentMigrateService;
import org.springframework.stereotype.Service;


@Service
public class StudentsUpdateService extends BaseUpdateService<DStudentModel, DStudentModelRepository, StudentModel, StudentModelRepository, StudentService, StudentMigrateService> {

    public StudentsUpdateService(StudentModelRepository repo, DStudentModelRepository dRepo, StudentMigrateService baseMigrateService, StudentService service) {
        super(repo, dRepo, baseMigrateService, service);
    }
}
