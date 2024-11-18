package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.lessons.V1ExamModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности типа экзамена.
 */
@Repository
public interface V1ExamModelRepository extends V1DBBaseModelRepository<V1ExamModel> {
}