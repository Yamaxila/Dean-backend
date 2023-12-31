package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.lessons.ExamModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности типа экзамена.
 */
@Repository
public interface ExamModelRepository extends DBBaseModelRepository<ExamModel> {
}