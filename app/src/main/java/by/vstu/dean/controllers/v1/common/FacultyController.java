package by.vstu.dean.controllers.v1.common;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.mapper.v1.FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с факультетами.
 */
@RestController
@RequestMapping("/api/v1/faculties/")
@Tag(name = "Faculties", description = "Факультеты")
public class FacultyController extends BaseController<FacultyDTO, FacultyModel, FacultyMapper, FacultyModelRepository, FacultyService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис факультетов
     */
    public FacultyController(FacultyService service, FacultyMapper mapper) {
        super(service, mapper);
    }

    /**
     * @param facultyModel Модель факультета
     */
    @Override
    @Operation(method = "", hidden = true)
    public ResponseEntity<FacultyDTO> put(FacultyDTO facultyModel) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @param id Идентификатор факультета
     */
    @Override
    @Operation(method = "", hidden = true)
    public ResponseEntity<FacultyDTO> deleteById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}