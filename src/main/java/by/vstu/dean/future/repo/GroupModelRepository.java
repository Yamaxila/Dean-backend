package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.GroupModel;

import java.util.List;

/**
 * Репозиторий для работы с сущностями GroupModel.
 */
public interface GroupModelRepository extends DBBaseModelRepository<GroupModel> {

    /**
     * Найти все группы, у которых специальность равна null.
     *
     * @return Список объектов GroupModel
     */
    List<GroupModel> findAllBySpecIsNull();

    /**
     * Найти группу по имени.
     *
     * @param name Имя группы
     * @return Объект GroupModel, соответствующий имени
     */
    GroupModel findByName(String name);
}
