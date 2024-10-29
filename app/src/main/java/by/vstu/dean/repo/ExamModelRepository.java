package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.ExamModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности типа экзамена.
 */
@Repository
public interface ExamModelRepository extends DBBaseModelRepository<ExamModel> {
}