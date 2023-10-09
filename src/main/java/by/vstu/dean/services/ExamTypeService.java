package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.ExamModel;
import by.vstu.dean.future.repo.ExamModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели типа экзамена.
 */
@Service
public class ExamTypeService extends BaseService<ExamModel, ExamModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса типа экзамена.
     *
     * @param repo Репозиторий для работы с моделью типа экзамена.
     */
    public ExamTypeService(ExamModelRepository repo) {
        super(repo);
    }
}
