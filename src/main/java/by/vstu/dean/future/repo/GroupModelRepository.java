package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.DBBaseModelRepository;

import java.util.List;

public interface GroupModelRepository extends DBBaseModelRepository<GroupModel> {
    GroupModel findBySourceId(Long id);

    List<GroupModel> findAllBySpecIsNull();

    GroupModel findByName(String name);
}