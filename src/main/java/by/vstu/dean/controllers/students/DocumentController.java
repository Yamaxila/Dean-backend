package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.students.DocumentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import by.vstu.dean.services.DocumentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students/documents/")
@Api(tags = {"Documents"}, description = "Документы")
public class DocumentController extends BaseController<DocumentModel, DocumentModelRepository, DocumentService> {
    public DocumentController(DocumentService service) {
        super(service);
    }
}
