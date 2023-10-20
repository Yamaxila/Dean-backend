package by.vstu.dean.services;

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
public class DocumentService extends BaseService<DocumentModel, DocumentModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса документа.
     *
     * @param repo Репозиторий для работы с моделью документа.
     */
    public DocumentService(DocumentModelRepository repo) {
        super(repo);
    }

    @Override
    @Cacheable(value = "documents", key = "id")
    public Optional<DocumentModel> getById(Long id) {
        return super.getById(id);
    }
}
