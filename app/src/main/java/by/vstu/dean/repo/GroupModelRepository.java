package by.vstu.dean.repo;

import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с сущностями GroupModel.
 */
@Repository
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
