package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.repo.DepartmentModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели кафедры.
 */
@Service
@Cacheable("department")
public class DepartmentService extends BaseService<DepartmentModel, DepartmentModelRepository> {
    /**
     * Конструктор для создания экземпляра сервиса кафедры.
     *
     * @param repo   Репозиторий для работы с моделью кафедры.
     */
    public DepartmentService(DepartmentModelRepository repo, Javers javers) {
        super(repo, javers);
    }


}
