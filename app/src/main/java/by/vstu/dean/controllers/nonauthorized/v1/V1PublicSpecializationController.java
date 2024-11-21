package by.vstu.dean.controllers.nonauthorized.v1;

import by.vstu.dean.core.controllers.PublicController;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.mapper.v1.V1SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/spez/")
@Tag(name = "Specializations", description = "Специализации")
public class V1PublicSpecializationController extends PublicController<V1SpecializationDTO, SpecializationModel, V1SpecializationMapper, SpecializationModelRepository, SpecializationService> {
    public V1PublicSpecializationController(SpecializationService service, V1SpecializationMapper mapper) {
        super(service, mapper);
    }
}
