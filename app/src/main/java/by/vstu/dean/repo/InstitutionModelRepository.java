package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.internal.InstitutionModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности учреждений образования.
 */
@Repository
public interface InstitutionModelRepository extends DBBaseModelRepository<InstitutionModel> {

}