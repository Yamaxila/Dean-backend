package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.students.DocumentDTO;
import by.vstu.dean.dto.mapper.DocumentMapper;
import by.vstu.dean.future.models.students.DocumentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.services.DocumentService;
import by.vstu.dean.services.migrate.DocumentMigrateService;
import org.springframework.stereotype.Service;

@Service
public class DocumentUpdateService extends BaseUpdateService<DocumentDTO, DStudentModel, DStudentModelRepository, DocumentModel, DocumentMapper, DocumentModelRepository, DocumentService, DocumentMigrateService>{

    public DocumentUpdateService(DocumentModelRepository repo, DStudentModelRepository dRepo, DocumentMigrateService baseMigrateService, DocumentService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
