package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.future.students.DocumentDTO;
import by.vstu.dean.dto.mapper.DocumentMapper;
import by.vstu.dean.future.models.students.DocumentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import by.vstu.dean.services.DocumentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с документами студентов.
 */
@RestController
@RequestMapping("/api/students/documents/")
@Api(tags = {"Documents"}, description = "Документы")
public class DocumentController extends BaseController<DocumentDTO, DocumentModel, DocumentMapper, DocumentModelRepository, DocumentService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис документов студентов
     */
    public DocumentController(DocumentService service) {
        super(service);
    }
}