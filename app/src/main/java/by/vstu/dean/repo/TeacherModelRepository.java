package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.TeacherModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности преподавателей.
 */
@Repository
public interface TeacherModelRepository extends DBBaseModelRepository<TeacherModel> {


}