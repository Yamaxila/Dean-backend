package by.vstu.dean.controllers.nonauthorized.v1;

import by.vstu.dean.core.controllers.PublicController;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.mapper.v1.V1FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/faculties/")
@Tag(name = "Faculties", description = "Факультеты")
public class V1PublicFacultyController extends PublicController<V1FacultyDTO, FacultyModel, V1FacultyMapper, FacultyModelRepository, FacultyService> {
    public V1PublicFacultyController(FacultyService service, V1FacultyMapper mapper) {
        super(service, mapper);
    }
}
