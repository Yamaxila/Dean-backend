package by.vstu.dean.services;

import by.vstu.dean.future.models.students.InstitutionModel;
import by.vstu.dean.future.repo.InstitutionModelRepository;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService extends BaseService<InstitutionModel, InstitutionModelRepository> {
    public InstitutionService(InstitutionModelRepository repo) {
        super(repo);
    }
}
