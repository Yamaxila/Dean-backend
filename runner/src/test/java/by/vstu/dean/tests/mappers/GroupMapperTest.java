package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.students.GroupDTO;
import by.vstu.dean.mapper.v1.GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.tests.BaseMapperTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class GroupMapperTest extends BaseMapperTest<GroupModel, GroupDTO, GroupMapper> {

    @Autowired
    private GroupService groupService;

    @Override
    public GroupModel getNewEntity() {
        return this.groupService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public GroupDTO getNewDTO() {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(this.getNewEntity().getId());
        groupDTO.setYearEnd(2024);
        return groupDTO;
    }
}
