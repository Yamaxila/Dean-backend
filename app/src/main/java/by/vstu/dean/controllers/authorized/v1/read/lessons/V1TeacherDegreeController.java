package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.lessons.V1TeacherDegreeDTO;
import by.vstu.dean.mapper.v1.V1TeacherDegreeMapper;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import by.vstu.dean.services.TeacherDegreeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с должностями преподавателей.
 */
@RestController
@RequestMapping("/api/v1/teachers/degrees/")
@Tag(name = "Degrees", description = "Должности преподавателей")
public class V1TeacherDegreeController extends BaseController<V1TeacherDegreeDTO, TeacherDegreeModel, V1TeacherDegreeMapper, TeacherDegreeModelRepository, TeacherDegreeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис должностей преподавателей
     * @param mapper Сервис должностей преподавателей
     */
    public V1TeacherDegreeController(TeacherDegreeService service, V1TeacherDegreeMapper mapper) {
        super(service, mapper);
    }
}