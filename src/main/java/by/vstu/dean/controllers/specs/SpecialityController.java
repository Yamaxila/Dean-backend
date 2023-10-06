package by.vstu.dean.controllers.specs;


import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/specs/")
@Api(tags = "Specialities", description = "Специальности")
public class SpecialityController extends BaseController<SpecialityModel, SpecialityModelRepository, SpecialityService> {
    public SpecialityController(SpecialityService service) {
        super(service);
    }

}
