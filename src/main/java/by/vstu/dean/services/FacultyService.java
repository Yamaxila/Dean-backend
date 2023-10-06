package by.vstu.dean.services;

import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService extends BaseService<FacultyModel, FacultyModelRepository> {

    public FacultyService(FacultyModelRepository repo) {
        super(repo);
    }

}
