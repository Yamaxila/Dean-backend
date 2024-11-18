package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1StatementModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности ведомостей.
 */
@Repository
public interface V1StatementModelRepository extends V1DBBaseModelRepository<V1StatementModel> {
}
