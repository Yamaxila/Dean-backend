package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.QualificationModel;

public interface QualificationModelRepository extends DBBaseModelRepository<QualificationModel> {


    QualificationModel findBySourceId(Long sourceId);

}