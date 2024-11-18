package by.vstu.dean.controllers.authorized.v1.read.common;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.mapper.v1.V1FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с факультетами.
 */
@RestController
@RequestMapping("/api/v1/faculties/")
@Tag(name = "Faculties", description = "Факультеты")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadFacultyController extends BaseReadController<V1FacultyDTO, FacultyModel, V1FacultyMapper, FacultyModelRepository, FacultyService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис факультетов
     */
    public V1ReadFacultyController(FacultyService service, V1FacultyMapper mapper) {
        super(service, mapper);
    }

    @Override
    @PreAuthorize("#hasAnyAuthority('read') AND hasAnyRole('ROLE_')")
    public ResponseEntity<List<V1FacultyDTO>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<List<V1FacultyDTO>> getAllActive(Boolean is) {
        return super.getAllActive(is);
    }

    @Override
    @PreAuthorize("#hasAnyAuthority('read') AND hasAnyRole('ROLE_')")
    public ResponseEntity<V1FacultyDTO> getById(Long id) {
        return super.getById(id);
    }

}