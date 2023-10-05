package by.vstu.dean.services;

import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecializationService extends BaseService<SpecializationModel, SpecializationModelRepository> {
    public SpecializationService(SpecializationModelRepository repo) {
        super(repo);
    }
}
