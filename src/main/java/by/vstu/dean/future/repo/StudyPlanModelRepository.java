package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.StudyPlanModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности учебных планов.
 */
@Repository
public interface StudyPlanModelRepository extends DBBaseModelRepository<StudyPlanModel> {
}