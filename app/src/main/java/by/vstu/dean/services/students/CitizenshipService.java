package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели гражданства.
 */
@Service
@Cacheable("citizenship")
public class CitizenshipService extends BaseService<CitizenshipModel, CitizenshipModelRepository> {
    /**
     * Конструктор для создания экземпляра сервиса гражданства.
     *
     * @param repo   Репозиторий для работы с моделью гражданства.
     */
    public CitizenshipService(CitizenshipModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }


}
