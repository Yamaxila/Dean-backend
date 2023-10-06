package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.common.BaseController;
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
public class DisciplineController extends BaseController<DisciplineModel, DisciplineModelRepository, DisciplineService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис дисциплин
     */
    public DisciplineController(DisciplineService service) {
        super(service);
    }
}