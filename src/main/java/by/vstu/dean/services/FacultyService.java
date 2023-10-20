package by.vstu.dean.services;

import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели факультета.
 */
@Service
@Cacheable("faculty")
public class FacultyService extends BaseService<FacultyModel, FacultyModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса факультета.
     *
     * @param repo Репозиторий для работы с моделью факультета.
     */
    public FacultyService(FacultyModelRepository repo) {
        super(repo);
    }

}
