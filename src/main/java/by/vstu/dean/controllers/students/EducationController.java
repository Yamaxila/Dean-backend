package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import by.vstu.dean.services.EducationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students/educations/")
@Api(tags = {"Educations"}, description = "Предыдущие образования")
public class EducationController extends BaseController<EducationModel, EducationModelRepository, EducationService> {
    public EducationController(EducationService service) {
        super(service);
    }
}
