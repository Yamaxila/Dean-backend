package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности языков студентов.
 */
@Repository
public interface StudentLanguageModelRepository extends DBBaseModelRepository<StudentLanguageModel> {

}