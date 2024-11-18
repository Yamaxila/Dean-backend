package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1StudentLanguageModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности языков студентов.
 */
@Repository
public interface V1StudentLanguageModelRepository extends V1DBBaseModelRepository<V1StudentLanguageModel> {

}