package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1InstitutionModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности учреждений образования.
 */
@Repository
public interface V1InstitutionModelRepository extends V1DBBaseModelRepository<V1InstitutionModel> {

}