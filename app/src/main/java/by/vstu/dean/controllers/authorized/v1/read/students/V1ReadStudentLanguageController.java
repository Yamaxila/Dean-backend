package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.students.V1StudentLanguageDTO;
import by.vstu.dean.mapper.v1.V1StudentLanguageMapper;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import by.vstu.dean.services.students.StudentLanguageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с языками студентов.
 */
@RestController
@RequestMapping("/api/v1/students/langs")
@Tag(name = "Languages", description = "Языки")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadStudentLanguageController extends BaseReadController<V1StudentLanguageDTO, StudentLanguageModel, V1StudentLanguageMapper, StudentLanguageModelRepository, StudentLanguageService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис языков студентов
     * @param mapper Маппер
     */
    public V1ReadStudentLanguageController(StudentLanguageService service, V1StudentLanguageMapper mapper) {
        super(service, mapper);
    }
}