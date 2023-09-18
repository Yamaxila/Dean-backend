package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.GroupModel;
import by.vstu.dean.future.DBBaseModelRepository;

public interface GroupModelRepository extends DBBaseModelRepository<GroupModel> {
    GroupModel findBySourceId(Long id);
}