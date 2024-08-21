package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.mapper.TeacherDegreeMapper;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import by.vstu.dean.services.TeacherDegreeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с должностями преподавателей.
 */
@RestController
@RequestMapping("/api/v1/teachers/degrees/")
@Api(tags = {"Degrees"}, description = "Должности преподавателей")
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