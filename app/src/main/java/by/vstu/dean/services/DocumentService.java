package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.DocumentModel;
import by.vstu.dean.repo.DocumentModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для работы с объектами модели документа.
 */
@Service
@Cacheable("document")
public class DocumentService extends BaseService<DocumentModel, DocumentModelRepository> {


    public DocumentService(DocumentModelRepository repo) {
        super(repo);
    }

    @Override
    public Optional<DocumentModel> getById(Long id) {
        return super.getById(id);
    }



}
