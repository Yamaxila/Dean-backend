package by.vstu.dean.services;

import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели специальности.
 */
@Service
@Cacheable("speciality")
public class SpecialityService extends BaseService<SpecialityModel, SpecialityModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса специальности.
     *
     * @param repo Репозиторий для работы с моделью специальности.
     */
    public SpecialityService(SpecialityModelRepository repo) {
        super(repo);
    }
}
