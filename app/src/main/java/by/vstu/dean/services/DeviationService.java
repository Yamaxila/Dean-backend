package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.DeviationModel;
import by.vstu.dean.repo.DeviationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели отклонений.
 */
@Service
@Cacheable("deviation")
public class DeviationService extends BaseService<DeviationModel, DeviationModelRepository> {
    public DeviationService(DeviationModelRepository repo) {
        super(repo);
    }

}