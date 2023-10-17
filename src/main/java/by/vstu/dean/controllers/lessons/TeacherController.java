package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.repo.TeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с преподавателями.
 */
@RestController
@RequestMapping("/api/teachers/")
@Api(tags = "Teachers", description = "Преподаватели")
public class TeacherController extends BaseController<TeacherModel, TeacherModelRepository, TeacherService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис преподавателей
     */
    public TeacherController(TeacherService service) {
        super(service);
    }
}