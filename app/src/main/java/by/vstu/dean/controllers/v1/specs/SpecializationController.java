package by.vstu.dean.controllers.v1.specs;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.mapper.v1.SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с специализациями.
 */
@RestController
@RequestMapping("/api/v1/spez/")
@Api(tags = "Specializations", description = "Специализации")
public class SpecializationController extends BaseController<SpecializationDTO, SpecializationModel, SpecializationMapper, SpecializationModelRepository, SpecializationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис специализаций
     * @param mapper Сервис специализаций
     */
    public SpecializationController(SpecializationService service, SpecializationMapper mapper) {
        super(service, mapper);
    }
}