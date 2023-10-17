package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности дисциплин.
 */
@Repository
public interface DisciplineModelRepository extends DBBaseModelRepository<DisciplineModel> {
}