package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.dto.mapper.QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.repo.QualificationModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели квалификации.
 */
@Service
@Cacheable("qualification")
public class QualificationService extends BaseService<QualificationDTO, QualificationModel, QualificationMapper, QualificationModelRepository> {


    public QualificationService(QualificationModelRepository repo, QualificationMapper mapper) {
        super(repo, mapper);
    }
}
