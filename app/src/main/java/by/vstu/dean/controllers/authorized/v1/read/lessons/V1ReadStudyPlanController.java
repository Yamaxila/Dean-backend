package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.lessons.V1StudyPlanDTO;
import by.vstu.dean.mapper.v1.V1StudyPlanMapper;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.repo.StudyPlanModelRepository;
import by.vstu.dean.services.StudyPlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с учебными планами.
 */
@RestController
@RequestMapping("/api/v1/plans/")
@Tag(name = "StudyPlans", description = "Учебные планы")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadStudyPlanController extends BaseReadController<V1StudyPlanDTO, StudyPlanModel, V1StudyPlanMapper, StudyPlanModelRepository, StudyPlanService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис учебных планов
     */
    public V1ReadStudyPlanController(StudyPlanService service, V1StudyPlanMapper mapper) {
        super(service, mapper);
    }
}