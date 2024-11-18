package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1DocumentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface V1DocumentModelRepository extends V1DBBaseModelRepository<V1DocumentModel> {
    @Query("select s.sourceId from V1DocumentModel s")
    List<Long> findAllSourceIds();

    boolean existsBySourceIdAndCaseNoAndEducationStringAndEducationYearEndAndJob(Long sourceId, Long caseNo, String educationString, Integer educationYearEnd, String job);

    List<V1DocumentModel> findBySourceIdAndCaseNoAndEducationStringAndEducationYearEndAndJob(Long sourceId, Long caseNo, String educationString, Integer educationYearEnd, String job);

    /**
     * Найти студента по номеру зачетки.
     *
     * @param caseNo Номер зачетки.
     * @return Опциональную модель студента, если он существует.
     */
    Optional<V1DocumentModel> findByCaseNo(Long caseNo);
}