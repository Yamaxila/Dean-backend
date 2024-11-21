package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.repo.InstitutionModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного учреждения.
 */
@Service
@Cacheable("institution")
public class InstitutionService extends BaseService<InstitutionModel, InstitutionModelRepository> {

    public InstitutionService(InstitutionModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }
}
