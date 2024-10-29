package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели специализации.
 */
@Service
@Cacheable("specialization")
public class SpecializationService extends BaseService<SpecializationModel, SpecializationModelRepository> {

    public SpecializationService(SpecializationModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
