package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.students.GroupDTO;
import by.vstu.dean.dto.mapper.GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели группы.
 */
@Service
@Cacheable("group")
public class GroupService extends BaseService<GroupDTO, GroupModel, GroupMapper, GroupModelRepository> {

    public GroupService(GroupModelRepository repo, GroupMapper mapper) {
        super(repo, mapper);
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
