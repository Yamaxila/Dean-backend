package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.internal.PassportModel;
import by.vstu.dean.repo.PassportModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

@Service
public class PassportService extends BaseService<PassportModel, PassportModelRepository> {
    public PassportService(PassportModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
