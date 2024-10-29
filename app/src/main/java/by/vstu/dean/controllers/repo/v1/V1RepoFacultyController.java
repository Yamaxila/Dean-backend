package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.mapper.v1.V1FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/faculties/")
@Tag(name = "Faculties", description = "Факультеты")
public class V1RepoFacultyController extends RepoController<V1FacultyDTO, FacultyModel, V1FacultyMapper, FacultyModelRepository, FacultyService> {

    public V1RepoFacultyController(FacultyService service, V1FacultyMapper mapper) {
        super(service, mapper);
    }

}
