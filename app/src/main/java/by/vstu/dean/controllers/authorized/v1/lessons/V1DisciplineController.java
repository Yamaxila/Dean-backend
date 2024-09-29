package by.vstu.dean.controllers.authorized.v1.lessons;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.lessons.V1DisciplineDTO;
import by.vstu.dean.mapper.v1.V1DisciplineMapper;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с дисциплинами.
 */
@RestController
@RequestMapping("/api/v1/disciplines/")
@Tag(name = "Disciplines", description = "Дисциплины")
public class V1DisciplineController extends BaseController<V1DisciplineDTO, DisciplineModel, V1DisciplineMapper, DisciplineModelRepository, DisciplineService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис дисциплин
     * @param mapper Маппер
     */
    public V1DisciplineController(DisciplineService service, V1DisciplineMapper mapper) {
        super(service, mapper);
    }
}