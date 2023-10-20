package by.vstu.dean.services;

import by.vstu.dean.future.models.students.StudentLanguageModel;
import by.vstu.dean.future.repo.StudentLanguageModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели языковой подготовки студента.
 */
@Service
@Cacheable("language")
public class StudentLanguageService extends BaseService<StudentLanguageModel, StudentLanguageModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса языковой подготовки студента.
     *
     * @param repo Репозиторий для работы с моделью языковой подготовки студента.
     */
    public StudentLanguageService(StudentLanguageModelRepository repo) {
        super(repo);
    }
}
