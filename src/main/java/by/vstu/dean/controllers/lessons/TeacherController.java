package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.BaseController;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.repo.TeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers/")
@Api(tags = "Teachers", description = "Предодаватели")
public class TeacherController extends BaseController<TeacherModel, TeacherModelRepository, TeacherService> {
    public TeacherController(TeacherService service) {
        super(service);
    }
}
