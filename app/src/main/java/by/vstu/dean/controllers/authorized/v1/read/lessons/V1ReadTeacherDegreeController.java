package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.lessons.V1TeacherDegreeDTO;
import by.vstu.dean.mapper.v1.V1TeacherDegreeMapper;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import by.vstu.dean.services.TeacherDegreeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с должностями преподавателей.
 */
@RestController
@RequestMapping("/api/v1/teachers/degrees")
@Tag(name = "Degrees", description = "Должности преподавателей")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadTeacherDegreeController extends BaseReadController<V1TeacherDegreeDTO, TeacherDegreeModel, V1TeacherDegreeMapper, TeacherDegreeModelRepository, TeacherDegreeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис должностей преподавателей
     * @param mapper Сервис должностей преподавателей
     */
    public V1ReadTeacherDegreeController(TeacherDegreeService service, V1TeacherDegreeMapper mapper) {
        super(service, mapper);
    }
}