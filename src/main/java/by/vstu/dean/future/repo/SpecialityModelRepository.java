package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.QualificationModel;
import by.vstu.dean.future.models.SpecialityModel;
import by.vstu.dean.future.DBBaseModelRepository;

public interface SpecialityModelRepository extends DBBaseModelRepository<SpecialityModel> {

    SpecialityModel findBySourceId(Long sourceId);

}