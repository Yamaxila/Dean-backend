package by.vstu.dean.services;

import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели специализации.
 */
@Service
@Cacheable("specialization")
public class SpecializationService extends BaseService<SpecializationModel, SpecializationModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса специализации.
     *
     * @param repo Репозиторий для работы с моделью специализации.
     */
    public SpecializationService(SpecializationModelRepository repo) {
        super(repo);
    }
}
