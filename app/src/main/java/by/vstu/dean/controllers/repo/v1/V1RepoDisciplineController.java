package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.lessons.V1DisciplineDTO;
import by.vstu.dean.mapper.v1.V1DisciplineMapper;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.repo.DisciplineModelRepository;
import by.vstu.dean.services.DisciplineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/disciplines/")
@Tag(name = "Discipline", description = "Дисциплины")
public class V1RepoDisciplineController extends RepoController<V1DisciplineDTO, DisciplineModel, V1DisciplineMapper, DisciplineModelRepository, DisciplineService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис
     * @param mapper  Маппер
     */
    public V1RepoDisciplineController(DisciplineService service, V1DisciplineMapper mapper) {
        super(service, mapper);
    }

}
