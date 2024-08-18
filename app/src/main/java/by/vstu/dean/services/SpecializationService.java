package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.dto.mapper.SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели специализации.
 */
@Service
@Cacheable("specialization")
public class SpecializationService extends BaseService<SpecializationDTO, SpecializationModel, SpecializationMapper, SpecializationModelRepository> {

    public SpecializationService(SpecializationModelRepository repo, SpecializationMapper mapper) {
        super(repo, mapper);
    }
}
