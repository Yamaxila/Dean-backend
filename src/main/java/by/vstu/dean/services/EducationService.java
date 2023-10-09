package by.vstu.dean.services;

import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели образования.
 */
@Service
public class EducationService extends BaseService<EducationModel, EducationModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса образования.
     *
     * @param repo Репозиторий для работы с моделью образования.
     */
    public EducationService(EducationModelRepository repo) {
        super(repo);
    }
}
