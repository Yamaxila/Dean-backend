package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.students.InstitutionModel;
import by.vstu.dean.future.repo.InstitutionModelRepository;
import by.vstu.dean.services.InstitutionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/institutions/")
@Api(tags = "Institutions", description = "Учреждения образования")
public class InstitutionController extends BaseController<InstitutionModel, InstitutionModelRepository, InstitutionService> {
    public InstitutionController(InstitutionService service) {
        super(service);
    }
}
