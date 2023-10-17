package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.DeviationModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности отклонений.
 */
@Deprecated
@Repository
public interface DeviationModelRepository extends DBBaseModelRepository<DeviationModel> {
}