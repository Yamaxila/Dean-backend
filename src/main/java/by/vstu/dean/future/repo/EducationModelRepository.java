package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.EducationModel;

import java.util.List;

public interface EducationModelRepository extends DBBaseModelRepository<EducationModel> {


    List<EducationModel> findAllBySourceId(Long id);

    List<EducationModel> findAllByStudentIdIsNull();
}