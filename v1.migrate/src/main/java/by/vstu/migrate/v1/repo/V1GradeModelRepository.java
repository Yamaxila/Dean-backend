package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1GradeModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности оценок.
 */
@Repository
public interface V1GradeModelRepository extends V1DBBaseModelRepository<V1GradeModel> {
}
