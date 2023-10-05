package by.vstu.dean.services;

import by.vstu.dean.future.models.students.StudentLanguageModel;
import by.vstu.dean.future.repo.StudentLanguageModelRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentLanguageService extends BaseService<StudentLanguageModel, StudentLanguageModelRepository> {
    public StudentLanguageService(StudentLanguageModelRepository repo) {
        super(repo);
    }
}
