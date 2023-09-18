package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.SpecializationModel;
import by.vstu.dean.future.DBBaseModelRepository;

public interface SpecializationModelRepository extends DBBaseModelRepository<SpecializationModel> {
    SpecializationModel findBySourceId(Long id);
}