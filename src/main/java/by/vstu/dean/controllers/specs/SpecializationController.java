package by.vstu.dean.controllers.specs;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с специализациями.
 */
@RestController
@RequestMapping("/api/spez/")
@Api(tags = "Specializations", description = "Специализации")
public class SpecializationController extends BaseController<SpecializationModel, SpecializationModelRepository, SpecializationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис специализаций
     */
    public SpecializationController(SpecializationService service) {
        super(service);
    }
}