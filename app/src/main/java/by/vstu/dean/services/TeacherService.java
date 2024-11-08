package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.repo.TeacherModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели преподавателя.
 */
@Service
@Cacheable("teacher")
public class TeacherService extends BaseService<TeacherModel, TeacherModelRepository> {


    public TeacherService(TeacherModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }
}
