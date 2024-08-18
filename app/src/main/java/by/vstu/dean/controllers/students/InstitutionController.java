package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.v1.students.InstitutionDTO;
import by.vstu.dean.dto.mapper.InstitutionMapper;
import by.vstu.dean.models.students.InstitutionModel;
import by.vstu.dean.repo.InstitutionModelRepository;
import by.vstu.dean.services.InstitutionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с учреждениями образования студентов.
 */
@RestController
@RequestMapping("/students/institutions/")
@Api(tags = "Institutions", description = "Учреждения образования")
public class InstitutionController extends BaseController<InstitutionDTO, InstitutionModel, InstitutionMapper, InstitutionModelRepository, InstitutionService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис учреждений образования студентов
     */
    public InstitutionController(InstitutionService service) {
        super(service);
    }
}