package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1DisciplineModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности дисциплин.
 */
@Repository
public interface V1DisciplineModelRepository extends V1DBBaseModelRepository<V1DisciplineModel> {
}