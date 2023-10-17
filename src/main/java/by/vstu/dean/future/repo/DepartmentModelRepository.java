package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности кафедр.
 */
@Repository
public interface DepartmentModelRepository extends DBBaseModelRepository<DepartmentModel> {
}