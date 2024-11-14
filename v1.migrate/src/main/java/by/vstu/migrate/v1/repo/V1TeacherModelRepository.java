package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1TeacherModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности преподавателей.
 */
@Repository
public interface V1TeacherModelRepository extends V1DBBaseModelRepository<V1TeacherModel> {


}