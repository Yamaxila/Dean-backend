package by.vstu.dean.controllers.students;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.future.students.CitizenshipDTO;
import by.vstu.dean.dto.mapper.CitizenshipMapper;
import by.vstu.dean.future.models.students.CitizenshipModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import by.vstu.dean.services.CitizenshipService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с гражданствами студентов.
 */
@RestController
@RequestMapping("/students/citizenship/")
@Api(tags = "Citizenship's", description = "Гражданства")
public class CitizenshipController extends BaseController<CitizenshipDTO, CitizenshipModel, CitizenshipMapper, CitizenshipModelRepository, CitizenshipService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис гражданств
     */
    public CitizenshipController(CitizenshipService service) {
        super(service);
    }
}