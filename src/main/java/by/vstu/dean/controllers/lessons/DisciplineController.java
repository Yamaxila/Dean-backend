package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.future.lessons.DisciplineDTO;
import by.vstu.dean.dto.mapper.DisciplineMapper;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.repo.DisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с дисциплинами.
 */
@RestController
@RequestMapping("/api/disciplines/")
@Api(tags = "Disciplines", description = "Дисциплины")
public class DisciplineController extends BaseController<DisciplineDTO, DisciplineModel, DisciplineMapper, DisciplineModelRepository, DisciplineService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис дисциплин
     */
    public DisciplineController(DisciplineService service) {
        super(service);
    }
}