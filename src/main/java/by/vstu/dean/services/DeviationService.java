package by.vstu.dean.services;

import by.vstu.dean.dto.future.students.DeviationDTO;
import by.vstu.dean.dto.mapper.DeviationMapper;
import by.vstu.dean.future.models.students.DeviationModel;
import by.vstu.dean.future.repo.DeviationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели отклонений.
 */
@Service
@Cacheable("deviation")
public class DeviationService extends BaseService<DeviationDTO, DeviationModel, DeviationMapper, DeviationModelRepository> {
    public DeviationService(DeviationModelRepository repo, DeviationMapper mapper) {
        super(repo, mapper);
    }

}
