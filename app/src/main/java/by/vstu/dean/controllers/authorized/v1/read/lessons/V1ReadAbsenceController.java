package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.lessons.V1AbsenceDTO;
import by.vstu.dean.mapper.v1.V1AbsenceMapper;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import by.vstu.dean.services.AbsenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отработками.
 */
@RestController
@RequestMapping("/api/v1/absences")
@Tag(name = "Absences", description = "Отработки")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadAbsenceController extends BaseReadController<V1AbsenceDTO, AbsenceModel, V1AbsenceMapper, AbsenceModelRepository, AbsenceService> {
    /**
     * Конструктор контроллера отработок.
     *
     * @param service Сервис для работы с отработками.
     * @param mapper Маппер отработок.
     */
    public V1ReadAbsenceController(AbsenceService service, V1AbsenceMapper mapper) {
        super(service, mapper);
    }
}
