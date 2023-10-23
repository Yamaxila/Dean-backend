package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.future.students.EducationDTO;
import by.vstu.dean.dto.mapper.EducationMapper;
import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import by.vstu.dean.services.EducationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с предыдущими образованиями студентов.
 */
@RestController
@RequestMapping("/api/students/educations/")
@Api(tags = {"Educations"}, description = "Предыдущие образования")
public class EducationController extends BaseController<EducationDTO, EducationModel, EducationMapper, EducationModelRepository, EducationService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис предыдущих образований студентов
     */
    public EducationController(EducationService service) {
        super(service);
    }
}