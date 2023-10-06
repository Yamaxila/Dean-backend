package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.TeacherDegreeModel;

public interface TeacherDegreeModelRepository extends DBBaseModelRepository<TeacherDegreeModel> {

    TeacherDegreeModel findByNameLike(String name);

}