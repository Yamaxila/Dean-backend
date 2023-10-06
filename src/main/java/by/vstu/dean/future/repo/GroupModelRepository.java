package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.GroupModel;

import java.util.List;

public interface GroupModelRepository extends DBBaseModelRepository<GroupModel> {


    List<GroupModel> findAllBySpecIsNull();

    GroupModel findByName(String name);
}