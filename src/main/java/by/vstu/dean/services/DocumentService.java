package by.vstu.dean.services;

import by.vstu.dean.future.models.students.DocumentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentService extends BaseService<DocumentModel, DocumentModelRepository> {
    public DocumentService(DocumentModelRepository repo) {
        super(repo);
    }
}
