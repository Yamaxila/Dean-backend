package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.repo.StudyPlanModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного плана.
 */
@Service
@Cacheable("studyplan")
public class StudyPlanService extends BaseService<StudyPlanModel, StudyPlanModelRepository> {


    public StudyPlanService(StudyPlanModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }
}
