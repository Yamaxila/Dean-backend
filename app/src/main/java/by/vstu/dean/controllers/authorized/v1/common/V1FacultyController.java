package by.vstu.dean.controllers.authorized.v1.common;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.mapper.v1.V1FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с факультетами.
 */
@RestController
@RequestMapping("/api/v1/faculties/")
@Tag(name = "Faculties", description = "Факультеты")
public class V1FacultyController extends BaseController<V1FacultyDTO, FacultyModel, V1FacultyMapper, FacultyModelRepository, FacultyService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис факультетов
     */
    public V1FacultyController(FacultyService service, V1FacultyMapper mapper) {
        super(service, mapper);
    }

    /**
     * @param dto DTO факультета
     */
    @Override
    @Operation(method = "", hidden = true)
    @PreAuthorize("#hasAnyAuthority('HIDDEN') AND isAnonymous()")
    public ResponseEntity<V1FacultyDTO> put(V1FacultyDTO dto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @param model Модель факультета
     */
    @Override
    @Operation(method = "", hidden = true)
    @PreAuthorize("#hasAnyAuthority('HIDDEN') AND isAnonymous()")
    public ResponseEntity<FacultyModel> putModel(FacultyModel model) {
        return super.putModel(model);
    }

    /**
     * @param id Идентификатор факультета
     */
    @Override
    @Operation(method = "", hidden = true)
    @PreAuthorize("#hasAnyAuthority('HIDDEN') AND isAnonymous()")
    public ResponseEntity<V1FacultyDTO> deleteById(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}