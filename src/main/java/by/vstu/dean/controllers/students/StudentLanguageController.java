package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.students.StudentLanguageModel;
import by.vstu.dean.future.repo.StudentLanguageModelRepository;
import by.vstu.dean.services.StudentLanguageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с языками студентов.
 */
@RestController
@RequestMapping("/api/students/langs/")
@Api(tags = {"Languages"}, description = "Языки")
public class StudentLanguageController extends BaseController<StudentLanguageModel, StudentLanguageModelRepository, StudentLanguageService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис языков студентов
     */
    public StudentLanguageController(StudentLanguageService service) {
        super(service);
    }
}