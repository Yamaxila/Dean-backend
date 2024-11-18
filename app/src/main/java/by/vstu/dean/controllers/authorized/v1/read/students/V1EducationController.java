package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.students.V1EducationDTO;
import by.vstu.dean.mapper.v1.V1EducationMapper;
import by.vstu.dean.models.students.internal.EducationModel;
import by.vstu.dean.repo.EducationModelRepository;
import by.vstu.dean.services.students.EducationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с предыдущими образованиями студентов.
 */
@RestController
@RequestMapping("/api/v1/students/educations/")
@Tag(name = "Educations", description = "Предыдущие образования")
public class V1EducationController extends BaseController<V1EducationDTO, EducationModel, V1EducationMapper, EducationModelRepository, EducationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис предыдущих образований студентов
     * @param mapper Маппер
     */
    public V1EducationController(EducationService service, V1EducationMapper mapper) {
        super(service, mapper);
    }
}