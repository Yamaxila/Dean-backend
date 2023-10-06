package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.students.DeviationModel;
import by.vstu.dean.future.repo.DeviationModelRepository;
import by.vstu.dean.services.DeviationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с отклонениями в данных студентов.
 */
@RestController
@RequestMapping("/api/students/deviations/")
@Api(tags = {"Deviations"}, description = "Отклонения")
public class DeviationController extends BaseController<DeviationModel, DeviationModelRepository, DeviationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис отклонений студентов
     */
    public DeviationController(DeviationService service) {
        super(service);
    }
}