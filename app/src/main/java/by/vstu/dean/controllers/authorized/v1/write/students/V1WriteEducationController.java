package by.vstu.dean.controllers.authorized.v1.write.students;

import by.vstu.dean.core.controllers.BaseWriteController;
import by.vstu.dean.dto.v1.students.V1EducationDTO;
import by.vstu.dean.mapper.v1.V1EducationMapper;
import by.vstu.dean.models.students.internal.EducationModel;
import by.vstu.dean.repo.EducationModelRepository;
import by.vstu.dean.services.students.EducationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с предыдущими образованиями студентов.
 */
@RestController
@RequestMapping("/api/v1/students/educations/")
@Tag(name = "Educations", description = "Предыдущие образования")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1WriteEducationController extends BaseWriteController<V1EducationDTO, EducationModel, V1EducationMapper, EducationModelRepository, EducationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис предыдущих образований студентов
     * @param mapper  Маппер
     */
    public V1WriteEducationController(EducationService service, V1EducationMapper mapper) {
        super(service, mapper);
    }
}