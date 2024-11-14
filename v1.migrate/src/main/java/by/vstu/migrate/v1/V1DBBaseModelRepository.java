package by.vstu.migrate.v1;

import by.vstu.migrate.v1.enums.V1EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Интерфейс репозитория базовой модели базы данных.
 *
 * @param <T> Сущность наследуемая от базовой модели.
 */
@NoRepositoryBean
public interface V1DBBaseModelRepository<T extends V1DBBaseModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

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
    List<T> findAllByStatus(V1EStatus status);
}
