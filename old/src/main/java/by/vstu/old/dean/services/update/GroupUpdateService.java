package by.vstu.old.dean.services.update;

import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import by.vstu.old.dean.models.DGroupModel;
import by.vstu.old.dean.repo.DGroupModelRepository;
import by.vstu.old.dean.services.migrate.GroupMigrateService;
import org.springframework.stereotype.Service;

@Service
public class GroupUpdateService extends BaseUpdateService<DGroupModel, DGroupModelRepository, GroupModel, GroupModelRepository, GroupService, GroupMigrateService> {
    public GroupUpdateService(GroupModelRepository repo, DGroupModelRepository dRepo, GroupMigrateService baseMigrateService, GroupService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
