package by.vstu.dean.controllers.v1.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.students.DeviationDTO;
import by.vstu.dean.mapper.v1.DeviationMapper;
import by.vstu.dean.models.students.DeviationModel;
import by.vstu.dean.repo.DeviationModelRepository;
import by.vstu.dean.services.DeviationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отклонениями в данных студентов.
 */
@RestController
@RequestMapping("/api/v1/students/deviations/")
@Api(tags = {"Deviations"}, description = "Отклонения")
public class DeviationController extends BaseController<DeviationDTO, DeviationModel, DeviationMapper, DeviationModelRepository, DeviationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис отклонений студентов
     * @param mapper Маппер
     */
    public DeviationController(DeviationService service, DeviationMapper mapper) {
        super(service, mapper);
    }
}