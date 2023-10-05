package by.vstu.dean.services;

import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends BaseService<GroupModel, GroupModelRepository> {

    public GroupService(GroupModelRepository repo) {
        super(repo);
    }

    public GroupModel findByName(String name) {
        return this.repo.findByName(name);
    }

}
