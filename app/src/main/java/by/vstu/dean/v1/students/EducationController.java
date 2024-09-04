package by.vstu.dean.v1.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.students.EducationDTO;
import by.vstu.dean.mapper.v1.EducationMapper;
import by.vstu.dean.models.students.EducationModel;
import by.vstu.dean.repo.EducationModelRepository;
import by.vstu.dean.services.EducationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с предыдущими образованиями студентов.
 */
@RestController
@RequestMapping("/api/v1/students/educations/")
@Tag(name = "Educations", description = "Предыдущие образования")
public class EducationController extends BaseController<EducationDTO, EducationModel, EducationMapper, EducationModelRepository, EducationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис предыдущих образований студентов
     * @param mapper Маппер
     */
    public EducationController(EducationService service, EducationMapper mapper) {
        super(service, mapper);
    }
}