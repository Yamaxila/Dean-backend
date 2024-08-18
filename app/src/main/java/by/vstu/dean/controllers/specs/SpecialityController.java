package by.vstu.dean.controllers.specs;

import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.dto.mapper.SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы со специальностями.
 */
@RestController
@RequestMapping("/api/specs/")
@Api(tags = "Specialities", description = "Специальности")
public class SpecialityController extends BaseController<SpecialityDTO, SpecialityModel, SpecialityMapper, SpecialityModelRepository, SpecialityService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис специальностей
     */
    public SpecialityController(SpecialityService service) {
        super(service);
    }
}