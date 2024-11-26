package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебной дисциплины.
 */
@Service
@Cacheable("discipline")
public class DisciplineService extends BaseService<DisciplineModel, DisciplineModelRepository> {

    public DisciplineService(DisciplineModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }
}
