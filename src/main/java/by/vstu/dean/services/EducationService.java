package by.vstu.dean.services;

import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import org.springframework.stereotype.Service;

@Service
public class EducationService extends BaseService<EducationModel, EducationModelRepository> {
    public EducationService(EducationModelRepository repo) {
        super(repo);
    }
}
