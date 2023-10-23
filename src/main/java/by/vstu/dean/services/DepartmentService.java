package by.vstu.dean.services;

import by.vstu.dean.dto.future.lessons.DepartmentDTO;
import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели кафедры.
 */
@Service
@Cacheable("department")
public class DepartmentService extends BaseService<DepartmentDTO, DepartmentModel, DepartmentMapper, DepartmentModelRepository> {
    /**
     * Конструктор для создания экземпляра сервиса кафедры.
     *
     * @param repo   Репозиторий для работы с моделью кафедры.
     * @param mapper Маппер.
     */
    public DepartmentService(DepartmentModelRepository repo, DepartmentMapper mapper) {
        super(repo, mapper);
    }


}
