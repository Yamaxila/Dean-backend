package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.students.V1InstitutionDTO;
import by.vstu.dean.mapper.v1.V1InstitutionMapper;
import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.repo.InstitutionModelRepository;
import by.vstu.dean.services.students.InstitutionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с учреждениями образования студентов.
 */
@RestController
@RequestMapping("/api/v1/institutions")
@Tag(name = "Institutions", description = "Учреждения образования")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadInstitutionController extends BaseReadController<V1InstitutionDTO, InstitutionModel, V1InstitutionMapper, InstitutionModelRepository, InstitutionService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис учреждений образования студентов
     * @param mapper Маппер
     */
    public V1ReadInstitutionController(InstitutionService service, V1InstitutionMapper mapper) {
        super(service, mapper);
    }
}