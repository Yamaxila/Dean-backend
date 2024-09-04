package by.vstu.dean.v1.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.students.StudentLanguageDTO;
import by.vstu.dean.mapper.v1.StudentLanguageMapper;
import by.vstu.dean.models.students.StudentLanguageModel;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import by.vstu.dean.services.StudentLanguageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с языками студентов.
 */
@RestController
@RequestMapping("/api/v1/students/langs/")
@Tag(name = "Languages", description = "Языки")
public class StudentLanguageController extends BaseController<StudentLanguageDTO, StudentLanguageModel, StudentLanguageMapper, StudentLanguageModelRepository, StudentLanguageService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис языков студентов
     * @param mapper Маппер
     */
    public StudentLanguageController(StudentLanguageService service, StudentLanguageMapper mapper) {
        super(service, mapper);
    }
}