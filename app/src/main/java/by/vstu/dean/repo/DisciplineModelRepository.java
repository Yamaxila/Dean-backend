package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.DisciplineModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности дисциплин.
 */
@Repository
public interface DisciplineModelRepository extends DBBaseModelRepository<DisciplineModel> {
}