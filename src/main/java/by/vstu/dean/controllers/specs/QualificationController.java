package by.vstu.dean.controllers.specs;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.specs.QualificationModel;
import by.vstu.dean.future.repo.QualificationModelRepository;
import by.vstu.dean.services.QualificationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qualifications/")
@Api(tags = "Qualification", description = "Квалификация")
public class QualificationController extends BaseController<QualificationModel, QualificationModelRepository, QualificationService> {
    public QualificationController(QualificationService service) {
        super(service);
    }
}
