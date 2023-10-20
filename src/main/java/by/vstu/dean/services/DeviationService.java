package by.vstu.dean.services;

import by.vstu.dean.future.models.students.DeviationModel;
import by.vstu.dean.future.repo.DeviationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели отклонений.
 */
@Service
@Cacheable("deviation")
public class DeviationService extends BaseService<DeviationModel, DeviationModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса отклонений.
     *
     * @param repo Репозиторий для работы с моделью отклонений.
     */
    public DeviationService(DeviationModelRepository repo) {
        super(repo);
    }
}
