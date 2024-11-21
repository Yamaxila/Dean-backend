package by.vstu.dean.core.repo;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Интерфейс репозитория базовой модели базы данных.
 *
 * @param <T> Сущность наследуемая от базовой модели.
 */
@NoRepositoryBean
public interface DBBaseModelRepository<T extends DBBaseModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    /**
     * Найти последнюю сущность по идентификатору в порядке убывания.
     *
     * @return Последняя базовая модель.
     */
    T findTopByOrderByIdDesc();

    /**
     * Найти все сущности по идентификатору источника.
     *
     * @param sourceId Идентификатор источника.
     * @return Список базовых моделей.
     */
    T findBySourceId(Long sourceId);

    /**
     * Найти все сущности по статусу.
     *
     * @param status Статус.
     * @return Список сущностей.
     */
    List<T> findAllByStatus(EStatus status);

    @Query("select m.sourceId from #{#entityName} m where m.id = :id")
    Long findSourceId(Long id);

}
