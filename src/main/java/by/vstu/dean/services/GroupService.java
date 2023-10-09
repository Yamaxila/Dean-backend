package by.vstu.dean.services;

import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели группы.
 */
@Service
public class GroupService extends BaseService<GroupModel, GroupModelRepository> {

    /**
     * Конструктор для создания экземпляра сервиса группы.
     *
     * @param repo Репозиторий для работы с моделью группы.
     */
    public GroupService(GroupModelRepository repo) {
        super(repo);
    }

    /**
     * Поиск группы по имени.
     *
     * @param name Имя группы для поиска.
     * @return Объект модели группы, соответствующий имени.
     */
    public GroupModel findByName(String name) {
        return this.repo.findByName(name);
    }
}
