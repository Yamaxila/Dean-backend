package by.vstu.dean.controllers.lessons;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.lessons.AbsenceModel;
import by.vstu.dean.future.repo.AbsenceModelRepository;
import by.vstu.dean.services.AbsenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/absences/")
public class AbsenceController extends BaseController<AbsenceModel, AbsenceModelRepository, AbsenceService> {
    public AbsenceController(AbsenceService service) {
        super(service);
    }
}
