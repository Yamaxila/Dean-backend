package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.DBBaseModelRepository;

public interface SpecialityModelRepository extends DBBaseModelRepository<SpecialityModel> {

    SpecialityModel findBySourceId(Long sourceId);

}