package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.mapper.v1.V1SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/repo/spez/")
public class V1RepoSpecializationController extends RepoController<V1SpecializationDTO, SpecializationModel, V1SpecializationMapper, SpecializationModelRepository, SpecializationService> {

    public V1RepoSpecializationController(SpecializationService service, V1SpecializationMapper mapper) {
        super(service, mapper);
    }

}
