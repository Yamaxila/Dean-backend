package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.InstitutionModel;

public interface InstitutionModelRepository extends DBBaseModelRepository<InstitutionModel> {
    InstitutionModel findBySourceId(Long aLong);
}