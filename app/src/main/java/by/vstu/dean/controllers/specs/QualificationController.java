package by.vstu.dean.controllers.specs;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.dto.mapper.QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.repo.QualificationModelRepository;
import by.vstu.dean.services.QualificationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с квалификациями.
 */
@RestController
@RequestMapping("/api/qualifications/")
@Api(tags = "Qualification", description = "Квалификация")
public class QualificationController extends BaseController<QualificationDTO, QualificationModel, QualificationMapper, QualificationModelRepository, QualificationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис квалификации
     */
    public QualificationController(QualificationService service) {
        super(service);
    }
}