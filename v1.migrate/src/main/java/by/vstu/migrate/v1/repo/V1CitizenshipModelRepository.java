package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1CitizenshipModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности гражданств.
 */
@Repository
public interface V1CitizenshipModelRepository extends V1DBBaseModelRepository<V1CitizenshipModel> {

}