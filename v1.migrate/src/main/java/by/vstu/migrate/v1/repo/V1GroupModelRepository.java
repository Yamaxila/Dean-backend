package by.vstu.migrate.v1.repo;

import by.vstu.migrate.v1.V1DBBaseModelRepository;
import by.vstu.migrate.v1.models.students.V1GroupModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями GroupModel.
 */
@Repository
public interface V1GroupModelRepository extends V1DBBaseModelRepository<V1GroupModel> {

    /**
     * Найти все группы, у которых специальность равна null.
     *
     * @return Список объектов GroupModel
     */
    List<V1GroupModel> findAllBySpecIsNull();

    /**
     * Найти группу по имени.
     *
     * @param name Имя группы
     * @return Объект GroupModel, соответствующий имени
     */
    V1GroupModel findByName(String name);
}
