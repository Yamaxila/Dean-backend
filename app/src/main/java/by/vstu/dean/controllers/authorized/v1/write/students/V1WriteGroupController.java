package by.vstu.dean.controllers.authorized.v1.write.students;

import by.vstu.dean.core.controllers.BaseWriteController;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с группами студентов.
 */
@RestController
@RequestMapping("/api/v1/groups")
@Tag(name = "Groups")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1WriteGroupController extends BaseWriteController<V1GroupDTO, GroupModel, V1GroupMapper, GroupModelRepository, GroupService> {
    public V1WriteGroupController(GroupService service, V1GroupMapper mapper) {
        super(service, mapper);
    }
}
