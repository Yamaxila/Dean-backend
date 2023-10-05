package by.vstu.dean.services;

import by.vstu.dean.future.models.students.DeviationModel;
import by.vstu.dean.future.repo.DeviationModelRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviationService extends BaseService<DeviationModel, DeviationModelRepository> {
    public DeviationService(DeviationModelRepository repo) {
        super(repo);
    }
}
