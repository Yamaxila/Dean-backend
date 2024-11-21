package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.DeviationModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности отклонений.
 */
@Deprecated
@Repository
public interface DeviationModelRepository extends DBBaseModelRepository<DeviationModel> {
}