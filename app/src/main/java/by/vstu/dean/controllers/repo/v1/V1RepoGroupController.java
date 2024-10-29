package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/groups/")
@Tag(name = "Groups", description = "Группы")
public class V1RepoGroupController extends RepoController<V1GroupDTO, GroupModel, V1GroupMapper, GroupModelRepository, GroupService> {
    public V1RepoGroupController(GroupService service, V1GroupMapper mapper) {
        super(service, mapper);
    }
}
