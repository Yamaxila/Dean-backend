package by.vstu.dean.services;

import by.vstu.dean.future.models.students.DocumentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели документа.
 */
@Service
public class DocumentService extends BaseService<DocumentModel, DocumentModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса документа.
     *
     * @param repo Репозиторий для работы с моделью документа.
     */
    public DocumentService(DocumentModelRepository repo) {
        super(repo);
    }
}
