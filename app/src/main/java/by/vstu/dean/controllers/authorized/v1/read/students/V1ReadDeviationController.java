package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.students.V1DeviationDTO;
import by.vstu.dean.mapper.v1.V1DeviationMapper;
import by.vstu.dean.models.students.DeviationModel;
import by.vstu.dean.repo.DeviationModelRepository;
import by.vstu.dean.services.DeviationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отклонениями в данных студентов.
 */
@RestController
@RequestMapping("/api/v1/students/deviations")
@Tag(name = "Deviations", description = "Отклонения")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadDeviationController extends BaseReadController<V1DeviationDTO, DeviationModel, V1DeviationMapper, DeviationModelRepository, DeviationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис отклонений студентов
     * @param mapper Маппер
     */
    public V1ReadDeviationController(DeviationService service, V1DeviationMapper mapper) {
        super(service, mapper);
    }
}