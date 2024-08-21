package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.StudentLanguageModel;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели языковой подготовки студента.
 */
@Service
@Cacheable("language")
public class StudentLanguageService extends BaseService<StudentLanguageModel, StudentLanguageModelRepository> {

    public StudentLanguageService(StudentLanguageModelRepository repo) {
        super(repo);
    }
}
