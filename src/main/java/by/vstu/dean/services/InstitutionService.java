package by.vstu.dean.services;

import by.vstu.dean.future.models.students.InstitutionModel;
import by.vstu.dean.future.repo.InstitutionModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного учреждения.
 */
@Service
@Cacheable("institution")
public class InstitutionService extends BaseService<InstitutionModel, InstitutionModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса учебного учреждения.
     *
     * @param repo Репозиторий для работы с моделью учебного учреждения.
     */
    public InstitutionService(InstitutionModelRepository repo) {
        super(repo);
    }
}
