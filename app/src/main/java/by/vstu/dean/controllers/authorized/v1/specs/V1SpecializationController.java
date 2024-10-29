package by.vstu.dean.controllers.authorized.v1.specs;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.mapper.v1.V1SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с специализациями.
 */
@RestController
@RequestMapping("/api/v1/spez/")
@Tag(name = "Specializations", description = "Специализации")
public class V1SpecializationController extends BaseController<V1SpecializationDTO, SpecializationModel, V1SpecializationMapper, SpecializationModelRepository, SpecializationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис специализаций
     * @param mapper Сервис специализаций
     */
    public V1SpecializationController(SpecializationService service, V1SpecializationMapper mapper) {
        super(service, mapper);
    }
}