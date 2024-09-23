package by.vstu.dean.services.changes;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.changes.StudentChangeModel;
import by.vstu.dean.repo.StudentChangeModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

@Service
public class StudentChangeService extends BaseService<StudentChangeModel, StudentChangeModelRepository> {

    public StudentChangeService(StudentChangeModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
