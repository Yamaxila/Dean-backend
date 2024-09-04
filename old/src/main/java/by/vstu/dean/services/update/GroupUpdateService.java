package by.vstu.dean.services.update;

import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.models.DGroupModel;
import by.vstu.dean.repo.DGroupModelRepository;
import by.vstu.dean.services.migrate.GroupMigrateService;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupUpdateService extends BaseUpdateService<DGroupModel, DGroupModelRepository, GroupModel, GroupModelRepository, GroupService, GroupMigrateService> {
    public GroupUpdateService(GroupModelRepository repo, DGroupModelRepository dRepo, GroupMigrateService baseMigrateService, GroupService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
