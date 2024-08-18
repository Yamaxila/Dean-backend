package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.dto.mapper.AbsenceMapper;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import by.vstu.dean.services.AbsenceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отработками.
 *
 * @apiNote Этот контроллер обрабатывает запросы, связанные с отработками студентов.
 */
@RestController
@RequestMapping("/api/v1/absences/")
@Api(tags = "Absences", description = "Отработки")
public class AbsenceController extends BaseController<AbsenceDTO, AbsenceModel, AbsenceMapper, AbsenceModelRepository, AbsenceService> {
    /**
     * Конструктор контроллера отработок.
     *
     * @param service Сервис для работы с отработками.
     */
    public AbsenceController(AbsenceService service) {
        super(service);
    }
}
