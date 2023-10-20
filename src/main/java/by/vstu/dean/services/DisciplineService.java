package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебной дисциплины.
 */
@Service
@Cacheable("discipline")
public class DisciplineService extends BaseService<DisciplineModel, DisciplineModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса учебной дисциплины.
     *
     * @param repo Репозиторий для работы с моделью учебной дисциплины.
     */
    public DisciplineService(DisciplineModelRepository repo) {
        super(repo);
    }
}
