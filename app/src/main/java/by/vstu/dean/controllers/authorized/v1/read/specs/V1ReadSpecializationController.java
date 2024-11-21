package by.vstu.dean.controllers.authorized.v1.read.specs;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.mapper.v1.V1SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с специализациями.
 */
@RestController
@RequestMapping("/api/v1/spez")
@Tag(name = "Specializations", description = "Специализации")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadSpecializationController extends BaseReadController<V1SpecializationDTO, SpecializationModel, V1SpecializationMapper, SpecializationModelRepository, SpecializationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис специализаций
     * @param mapper Сервис специализаций
     */
    public V1ReadSpecializationController(SpecializationService service, V1SpecializationMapper mapper) {
        super(service, mapper);
    }
}