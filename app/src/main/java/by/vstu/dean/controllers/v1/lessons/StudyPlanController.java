package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.mapper.StudyPlanMapper;
import by.vstu.dean.dto.v1.lessons.StudyPlanDTO;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.repo.StudyPlanModelRepository;
import by.vstu.dean.services.StudyPlanService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с учебными планами.
 */
@RestController
@RequestMapping("/api/v1/plans/")
@Api(tags = "StudyPlans", description = "Учебные планы")
public class StudyPlanController extends BaseController<StudyPlanDTO, StudyPlanModel, StudyPlanMapper, StudyPlanModelRepository, StudyPlanService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис учебных планов
     */
    public StudyPlanController(StudyPlanService service, StudyPlanMapper mapper) {
        super(service, mapper);
    }
}