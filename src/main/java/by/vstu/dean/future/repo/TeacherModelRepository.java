package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.TeacherModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности преподавателей.
 */
@Repository
public interface TeacherModelRepository extends DBBaseModelRepository<TeacherModel> {


}