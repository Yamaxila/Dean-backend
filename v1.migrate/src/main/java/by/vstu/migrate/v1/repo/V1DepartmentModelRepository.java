package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1DepartmentModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности кафедр.
 */
@Repository
public interface V1DepartmentModelRepository extends V1DBBaseModelRepository<V1DepartmentModel> {
}