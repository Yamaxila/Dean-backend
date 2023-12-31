package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.StudentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс репозитория сущности студента.
 */
@Repository
public interface StudentModelRepository extends DBBaseModelRepository<StudentModel> {

    /**
     * Найти id деканата всех пользователей.
     *
     * @return Список идентификаторов источников.
     */
    @Query("select s.sourceId from StudentModel s")
    List<Long> findAllSourceIds();

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

}