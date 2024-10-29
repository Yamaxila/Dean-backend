package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.StudyPlanModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности учебных планов.
 */
@Repository
public interface StudyPlanModelRepository extends DBBaseModelRepository<StudyPlanModel> {
}