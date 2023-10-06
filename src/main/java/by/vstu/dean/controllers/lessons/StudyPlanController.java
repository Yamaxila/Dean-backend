package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.lessons.StudyPlanModel;
import by.vstu.dean.future.repo.StudyPlanModelRepository;
import by.vstu.dean.services.StudyPlanService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с учебными планами.
 */
@RestController
@RequestMapping("/api/plans/")
@Api(tags = "StudyPlans", description = "Учебные планы")
public class StudyPlanController extends BaseController<StudyPlanModel, StudyPlanModelRepository, StudyPlanService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис учебных планов
     */
    public StudyPlanController(StudyPlanService service) {
        super(service);
    }
}