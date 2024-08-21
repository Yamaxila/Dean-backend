package by.vstu.dean.old.services.update;

import by.vstu.dean.models.students.DocumentModel;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import by.vstu.dean.old.services.migrate.DocumentMigrateService;
import by.vstu.dean.repo.DocumentModelRepository;
import by.vstu.dean.services.DocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentUpdateService extends BaseUpdateService<DStudentModel, DStudentModelRepository, DocumentModel, DocumentModelRepository, DocumentService, DocumentMigrateService> {

    public DocumentUpdateService(DocumentModelRepository repo, DStudentModelRepository dRepo, DocumentMigrateService baseMigrateService, DocumentService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
