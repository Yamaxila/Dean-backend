package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1StudentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс репозитория сущности студента.
 */
@Repository
public interface V1StudentModelRepository extends V1DBBaseModelRepository<V1StudentModel> {

    /**
     * Найти id деканата всех пользователей.
     *
     * @return Список идентификаторов источников.
     */
    @Query("select s.sourceId from V1StudentModel s")
    List<Long> findAllSourceIds();

    /**
     * Найти последнюю сущность по идентификатору группы с ненулевой специализацией.
     *
     * @param id Идентификатор группы.
     * @return Базовая модель студента.
     */
    V1StudentModel findTopByGroupIdAndSpecializationNotNull(Long id);

    /**
     * Найти всех студентов по идентификатору группы.
     *
     * @param id Идентификатор группы.
     * @return Список базовых моделей студентов.
     */
    List<V1StudentModel> findAllByGroupId(Long id);

}