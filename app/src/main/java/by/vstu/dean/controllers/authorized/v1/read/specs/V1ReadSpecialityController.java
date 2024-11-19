package by.vstu.dean.controllers.authorized.v1.read.specs;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.specs.V1SpecialityDTO;
import by.vstu.dean.mapper.v1.V1SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы со специальностями.
 */
@RestController
@RequestMapping("/api/v1/specs/")
@Tag(name = "Specialities", description = "Специальности")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadSpecialityController extends BaseReadController<V1SpecialityDTO, SpecialityModel, V1SpecialityMapper, SpecialityModelRepository, SpecialityService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис специальностей
     * @param mapper Сервис специальностей
     */
    public V1ReadSpecialityController(SpecialityService service, V1SpecialityMapper mapper) {
        super(service, mapper);
    }
}