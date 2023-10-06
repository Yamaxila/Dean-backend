package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.lessons.TeacherDegreeModel;
import by.vstu.dean.future.repo.TeacherDegreeModelRepository;
import by.vstu.dean.services.TeacherDegreeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с должностями преподавателей.
 */
@RestController
@RequestMapping("/api/teachers/degrees/")
@Api(tags = {"Degrees"}, description = "Должности преподавателей")
public class TeacherDegreeController extends BaseController<TeacherDegreeModel, TeacherDegreeModelRepository, TeacherDegreeService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис должностей преподавателей
     */
    public TeacherDegreeController(TeacherDegreeService service) {
        super(service);
    }
}