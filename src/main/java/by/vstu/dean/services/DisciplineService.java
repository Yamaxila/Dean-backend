package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import org.springframework.stereotype.Service;

@Service

public class DisciplineService extends BaseService<DisciplineModel, DisciplineModelRepository> {
    public DisciplineService(DisciplineModelRepository repo) {
        super(repo);
    }
}
