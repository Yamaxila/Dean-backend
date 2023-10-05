package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends BaseService<DepartmentModel, DepartmentModelRepository> {
    public DepartmentService(DepartmentModelRepository repo) {
        super(repo);
    }

}
