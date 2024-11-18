package by.vstu.dean.controllers.authorized.v1.read.specs;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.specs.V1QualificationDTO;
import by.vstu.dean.mapper.v1.V1QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.repo.QualificationModelRepository;
import by.vstu.dean.services.QualificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с квалификациями.
 */
@RestController
@RequestMapping("/api/v1/qualifications/")
@Tag(name = "Qualification", description = "Квалификация")
public class V1QualificationController extends BaseController<V1QualificationDTO, QualificationModel, V1QualificationMapper, QualificationModelRepository, QualificationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис квалификации
     * @param mapper Маппер
     */
    public V1QualificationController(QualificationService service, V1QualificationMapper mapper) {
        super(service, mapper);
    }
}