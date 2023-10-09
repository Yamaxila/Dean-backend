package by.vstu.dean.services;

import by.vstu.dean.future.models.students.CitizenshipModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели гражданства.
 */
@Service
public class CitizenshipService extends BaseService<CitizenshipModel, CitizenshipModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса гражданства.
     *
     * @param repo Репозиторий для работы с моделью гражданства.
     */
    public CitizenshipService(CitizenshipModelRepository repo) {
        super(repo);
    }
}
