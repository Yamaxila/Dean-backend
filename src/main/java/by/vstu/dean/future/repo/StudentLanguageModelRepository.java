package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.StudentLanguageModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности языков студентов.
 */
@Repository
public interface StudentLanguageModelRepository extends DBBaseModelRepository<StudentLanguageModel> {

}