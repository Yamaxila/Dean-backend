package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория сущности гражданств.
 */
@Repository
public interface CitizenshipModelRepository extends DBBaseModelRepository<CitizenshipModel> {

}