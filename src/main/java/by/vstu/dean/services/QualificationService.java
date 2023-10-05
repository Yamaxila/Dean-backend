package by.vstu.dean.services;

import by.vstu.dean.future.models.specs.QualificationModel;
import by.vstu.dean.future.repo.QualificationModelRepository;
import org.springframework.stereotype.Service;

@Service
public class QualificationService extends BaseService<QualificationModel, QualificationModelRepository>{
    public QualificationService(QualificationModelRepository repo) {
        super(repo);
    }
}
