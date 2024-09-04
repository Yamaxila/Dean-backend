package by.vstu.dean.controllers.v1.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.students.DocumentDTO;
import by.vstu.dean.mapper.v1.DocumentMapper;
import by.vstu.dean.models.students.DocumentModel;
import by.vstu.dean.repo.DocumentModelRepository;
import by.vstu.dean.services.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с документами студентов.
 */
@RestController
@RequestMapping("/api/v1/students/documents/")
@Tag(name = "Documents", description = "Документы")
public class DocumentController extends BaseController<DocumentDTO, DocumentModel, DocumentMapper, DocumentModelRepository, DocumentService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис документов студентов
     * @param mapper Маппер
     */
    public DocumentController(DocumentService service, DocumentMapper mapper) {
        super(service, mapper);
    }
}