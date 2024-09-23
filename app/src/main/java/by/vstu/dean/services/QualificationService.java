package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.repo.QualificationModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели квалификации.
 */
@Service
@Cacheable("qualification")
public class QualificationService extends BaseService<QualificationModel, QualificationModelRepository> {


    public QualificationService(QualificationModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
