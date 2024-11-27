package by.vstu.dean.repo;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.StudentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория сущности студента.
 */
@Repository
public interface StudentModelRepository extends DBBaseModelRepository<StudentModel> {

    /**
     * Найти id деканата всех студентов.
     *
     * @return Список идентификаторов источников.
     */
    @Query("select s.sourceId from StudentModel s")
    List<Long> findAllSourceIds();

    /**
     * Найти id деканата всех студентов по статусу.
     *
     * @return Список идентификаторов источников.
     */
    @Query("select s.sourceId from StudentModel s where s.status = :status")
    List<Long> findAllSourceIdsByStatus(EStatus status);

    /**
     * Найти последнюю сущность по идентификатору группы с ненулевой специализацией.
     *
     * @param id Идентификатор группы.
     * @return Базовая модель студента.
     */
    StudentModel findTopByGroupIdAndSpecializationNotNull(Long id);

    /**
     * Найти всех студентов по идентификатору группы.
     *
     * @param id Идентификатор группы.
     * @return Список базовых моделей студентов.
     */
    List<StudentModel> findAllByGroupId(Long id);

    /**
     * Найти студента по номеру зачетки.
     *
     * @param caseNo Номер зачетки.
     * @return Опциональную модель студента, если он существует.
     */
    Optional<StudentModel> findByCaseNo(Long caseNo);

    /**
     * Найти студентов по номеру зачетки.
     *
     * @param caseNo Номер зачетки.
     * @return Список студентов, если они существуют.
     */
    @Query("select s from StudentModel s where cast(s.caseNo as string) LIKE concat(:caseNo, '%')")
    List<StudentModel> findAllByCaseNo(String caseNo);

}