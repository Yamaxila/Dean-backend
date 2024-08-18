package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.dto.mapper.DisciplineMapper;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебной дисциплины.
 */
@Service
@Cacheable("discipline")
public class DisciplineService extends BaseService<DisciplineDTO, DisciplineModel, DisciplineMapper, DisciplineModelRepository> {

    public DisciplineService(DisciplineModelRepository repo, DisciplineMapper mapper) {
        super(repo, mapper);
    }
}
