package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели специальности.
 */
@Service
@Cacheable("speciality")
public class SpecialityService extends BaseService<SpecialityModel, SpecialityModelRepository> {


    public SpecialityService(SpecialityModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }
}
