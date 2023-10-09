package by.vstu.dean.services;

import by.vstu.dean.future.models.specs.QualificationModel;
import by.vstu.dean.future.repo.QualificationModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели квалификации.
 */
@Service
public class QualificationService extends BaseService<QualificationModel, QualificationModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса квалификации.
     *
     * @param repo Репозиторий для работы с моделью квалификации.
     */
    public QualificationService(QualificationModelRepository repo) {
        super(repo);
    }
}
