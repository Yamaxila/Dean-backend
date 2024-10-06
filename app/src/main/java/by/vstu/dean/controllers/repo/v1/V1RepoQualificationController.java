package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.specs.V1QualificationDTO;
import by.vstu.dean.mapper.v1.V1QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.repo.QualificationModelRepository;
import by.vstu.dean.services.QualificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/qualifications/")
@Tag(name = "Qualification", description = "Квалификация")
public class V1RepoQualificationController extends RepoController<V1QualificationDTO, QualificationModel, V1QualificationMapper, QualificationModelRepository, QualificationService> {

    public V1RepoQualificationController(QualificationService service, V1QualificationMapper mapper) {
        super(service, mapper);
    }

}
