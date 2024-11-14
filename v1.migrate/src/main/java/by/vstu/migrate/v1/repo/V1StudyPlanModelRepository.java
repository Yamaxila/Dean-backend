package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1StudyPlanModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности учебных планов.
 */
@Repository
public interface V1StudyPlanModelRepository extends V1DBBaseModelRepository<V1StudyPlanModel> {
}