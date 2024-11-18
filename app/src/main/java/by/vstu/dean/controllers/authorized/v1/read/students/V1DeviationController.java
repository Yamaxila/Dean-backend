package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.students.V1DeviationDTO;
import by.vstu.dean.mapper.v1.V1DeviationMapper;
import by.vstu.dean.models.students.DeviationModel;
import by.vstu.dean.repo.DeviationModelRepository;
import by.vstu.dean.services.DeviationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отклонениями в данных студентов.
 */
@RestController
@RequestMapping("/api/v1/students/deviations/")
@Tag(name = "Deviations", description = "Отклонения")
public class V1DeviationController extends BaseController<V1DeviationDTO, DeviationModel, V1DeviationMapper, DeviationModelRepository, DeviationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис отклонений студентов
     * @param mapper Маппер
     */
    public V1DeviationController(DeviationService service, V1DeviationMapper mapper) {
        super(service, mapper);
    }
}