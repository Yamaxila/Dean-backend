package by.vstu.dean.old.services.update;

import by.vstu.dean.dto.v1.students.GroupDTO;
import by.vstu.dean.dto.mapper.GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.old.models.DGroupModel;
import by.vstu.dean.old.repo.DGroupModelRepository;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.old.services.migrate.GroupMigrateService;
import org.springframework.stereotype.Service;

@Service
public class GroupUpdateService extends BaseUpdateService<GroupDTO, DGroupModel, DGroupModelRepository, GroupModel, GroupMapper, GroupModelRepository, GroupService, GroupMigrateService> {
    public GroupUpdateService(GroupModelRepository repo, DGroupModelRepository dRepo, GroupMigrateService baseMigrateService, GroupService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
