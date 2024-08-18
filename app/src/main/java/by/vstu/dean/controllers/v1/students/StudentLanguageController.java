package by.vstu.dean.controllers.v1.students;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.students.StudentLanguageDTO;
import by.vstu.dean.dto.mapper.StudentLanguageMapper;
import by.vstu.dean.models.students.StudentLanguageModel;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import by.vstu.dean.services.StudentLanguageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с языками студентов.
 */
@RestController
@RequestMapping("/api/v1/students/langs/")
@Api(tags = {"Languages"}, description = "Языки")
public class StudentLanguageController extends BaseController<StudentLanguageDTO, StudentLanguageModel, StudentLanguageMapper, StudentLanguageModelRepository, StudentLanguageService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис языков студентов
     */
    public StudentLanguageController(StudentLanguageService service) {
        super(service);
    }
}