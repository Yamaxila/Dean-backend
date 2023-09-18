package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.DBBaseModelRepository;

public interface FacultyModelRepository extends DBBaseModelRepository<FacultyModel> {
    FacultyModel findBySourceId(Long sourceId);
}