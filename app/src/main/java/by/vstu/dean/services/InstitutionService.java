package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.InstitutionModel;
import by.vstu.dean.repo.InstitutionModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного учреждения.
 */
@Service
@Cacheable("institution")
public class InstitutionService extends BaseService<InstitutionModel, InstitutionModelRepository> {

    public InstitutionService(InstitutionModelRepository repo) {
        super(repo);
    }
}
