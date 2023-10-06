package by.vstu.dean.controllers.common;

import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с факультетами.
 */
@RestController
@RequestMapping("api/faculties/")
@Api(tags = "Faculties")
public class FacultyController extends BaseController<FacultyModel, FacultyModelRepository, FacultyService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис факультетов
     */
    public FacultyController(FacultyService service) {
        super(service);
    }

    /**
     *
     * @param facultyModel Модель факультета
     * @throws SecurityException Can't execute this!
     */
    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<FacultyModel> put(FacultyModel facultyModel){
        throw new SecurityException("Can't execute this!");
    }

    /**
     *
     * @param id Идентификатор факультета
     * @throws SecurityException Can't execute this!
     */
    @Override
    @ApiOperation(value = "", hidden = true)
    public ResponseEntity<FacultyModel> deleteById(Long id) {
        throw new SecurityException("Can't execute this!");
    }
}