package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.DepartmentModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности кафедр.
 */
@Repository
public interface DepartmentModelRepository extends DBBaseModelRepository<DepartmentModel> {
}