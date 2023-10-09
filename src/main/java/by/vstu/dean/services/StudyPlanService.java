package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.StudyPlanModel;
import by.vstu.dean.future.repo.StudyPlanModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели учебного плана.
 */
@Service
public class StudyPlanService extends BaseService<StudyPlanModel, StudyPlanModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса учебного плана.
     *
     * @param repo Репозиторий для работы с моделью учебного плана.
     */
    public StudyPlanService(StudyPlanModelRepository repo) {
        super(repo);
    }
}
