package by.vstu.dean.controllers.v1.students;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.mapper.v1.CitizenshipMapper;
import by.vstu.dean.models.students.CitizenshipModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import by.vstu.dean.services.CitizenshipService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с гражданствами студентов.
 */
@RestController
@RequestMapping("/api/v1/students/citizenship/")
@Api(tags = "Citizenship's", description = "Гражданства")
public class CitizenshipController extends BaseController<CitizenshipDTO, CitizenshipModel, CitizenshipMapper, CitizenshipModelRepository, CitizenshipService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис гражданств
     * @param mapper Маппер
     */
    public CitizenshipController(CitizenshipService service, CitizenshipMapper mapper) {
        super(service, mapper);
    }
}