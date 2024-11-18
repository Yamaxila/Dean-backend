package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.lessons.V1AbsenceDTO;
import by.vstu.dean.mapper.v1.V1AbsenceMapper;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import by.vstu.dean.services.AbsenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отработками.
 *
 * @apiNote Этот контроллер обрабатывает запросы, связанные с отработками студентов.
 */
@RestController
@RequestMapping("/api/v1/absences/")
@Tag(name = "Absences", description = "Отработки")
public class V1AbsenceController extends BaseReadController<V1AbsenceDTO, AbsenceModel, V1AbsenceMapper, AbsenceModelRepository, AbsenceService> {
    /**
     * Конструктор контроллера отработок.
     *
     * @param service Сервис для работы с отработками.
     */
    public V1AbsenceController(AbsenceService service, V1AbsenceMapper mapper) {
        super(service, mapper);
    }
}
