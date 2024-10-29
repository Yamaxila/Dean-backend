package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.specs.V1SpecialityDTO;
import by.vstu.dean.mapper.v1.V1SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/specs/")
@Tag(name = "Specialities", description = "Специальности")
public class V1RepoSpecialityController extends RepoController<V1SpecialityDTO, SpecialityModel, V1SpecialityMapper, SpecialityModelRepository, SpecialityService> {

    public V1RepoSpecialityController(SpecialityService service, V1SpecialityMapper mapper) {
        super(service, mapper);
    }

}
