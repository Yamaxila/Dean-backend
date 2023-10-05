package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.ExamModel;
import by.vstu.dean.future.repo.ExamModelRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamTypeService extends BaseService<ExamModel, ExamModelRepository> {
    public ExamTypeService(ExamModelRepository repo) {
        super(repo);
    }
}
