package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.mapper.v1.DisciplineMapper;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с дисциплинами.
 */
@RestController
@RequestMapping("/api/v1/disciplines/")
@Api(tags = "Disciplines", description = "Дисциплины")
public class DisciplineController extends BaseController<DisciplineDTO, DisciplineModel, DisciplineMapper, DisciplineModelRepository, DisciplineService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис дисциплин
     * @param mapper Маппер
     */
    public DisciplineController(DisciplineService service, DisciplineMapper mapper) {
        super(service, mapper);
    }
}