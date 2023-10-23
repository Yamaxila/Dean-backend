package by.vstu.dean.services;

import by.vstu.dean.dto.future.students.DocumentDTO;
import by.vstu.dean.dto.mapper.DocumentMapper;
import by.vstu.dean.future.models.students.DocumentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для работы с объектами модели документа.
 */
@Service
@Cacheable("document")
public class DocumentService extends BaseService<DocumentDTO, DocumentModel, DocumentMapper, DocumentModelRepository> {


    public DocumentService(DocumentModelRepository repo, DocumentMapper mapper) {
        super(repo, mapper);
    }

    @Override
    @Cacheable(value = "documents", key = "id")
    public Optional<DocumentModel> getById(Long id) {
        return super.getById(id);
    }
}
