package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели группы.
 */
@Service
@Cacheable("group")
public class GroupService extends BaseService<GroupModel, GroupModelRepository> {

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