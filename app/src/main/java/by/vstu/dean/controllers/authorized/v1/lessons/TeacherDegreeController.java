package by.vstu.dean.controllers.authorized.v1.lessons;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.mapper.v1.TeacherDegreeMapper;
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
public class TeacherDegreeController extends BaseController<TeacherDegreeDTO, TeacherDegreeModel, TeacherDegreeMapper, TeacherDegreeModelRepository, TeacherDegreeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис должностей преподавателей
     * @param mapper Сервис должностей преподавателей
     */
    public TeacherDegreeController(TeacherDegreeService service, TeacherDegreeMapper mapper) {
        super(service, mapper);
    }
}