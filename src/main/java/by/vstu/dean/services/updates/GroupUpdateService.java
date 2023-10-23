package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.students.GroupDTO;
import by.vstu.dean.dto.mapper.GroupMapper;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.old.models.DGroupModel;
import by.vstu.dean.old.repo.DGroupModelRepository;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.migrate.GroupMigrateService;
import org.springframework.stereotype.Service;

@Service
public class GroupUpdateService extends BaseUpdateService<GroupDTO, DGroupModel, DGroupModelRepository, GroupModel, GroupMapper, GroupModelRepository, GroupService, GroupMigrateService> {
    public GroupUpdateService(GroupModelRepository repo, DGroupModelRepository dRepo, GroupMigrateService baseMigrateService, GroupService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
