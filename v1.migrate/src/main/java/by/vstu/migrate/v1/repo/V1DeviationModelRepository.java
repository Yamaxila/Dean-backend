package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1DeviationModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности отклонений.
 */
@Deprecated
@Repository
public interface V1DeviationModelRepository extends V1DBBaseModelRepository<V1DeviationModel> {
}