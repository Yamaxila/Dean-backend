package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.BaseController;
import by.vstu.dean.future.models.students.CitizenshipModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import by.vstu.dean.services.CitizenshipService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/citizenship/")
@Api(tags = "Citizenship's", description = "Гражданства")
public class CitizenshipController extends BaseController<CitizenshipModel, CitizenshipModelRepository, CitizenshipService> {
    public CitizenshipController(CitizenshipService service) {
        super(service);
    }
}
