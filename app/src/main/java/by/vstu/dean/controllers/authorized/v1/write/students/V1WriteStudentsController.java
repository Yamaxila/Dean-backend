package by.vstu.dean.controllers.authorized.v1.write.students;

import by.vstu.dean.core.controllers.BaseWriteController;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.students.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Контроллер для работы с объектами студентов.
 */
@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Students", description = "Студенты")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1WriteStudentsController extends BaseWriteController<V1StudentDTO, StudentModel, V1StudentMapper, StudentModelRepository, StudentService> {
    public V1WriteStudentsController(StudentService service, V1StudentMapper mapper) {
        super(service, mapper);
    }

    //TODO: перенести в /hostels/

    /**
     * Установить поле approved студенту по идентификатору.
     *
     * @param id       Идентификатор студента
     * @param approved Значение approved(true/false)
     * @return Объект студента
     */
    @RequestMapping(value = "/{id}/approve",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @Operation(method = "setApproved", description = "Устанавливает поле approved для объекта по id")
    public ResponseEntity<V1StudentDTO> setApproved(@PathVariable Long id, @RequestParam boolean approved) {
        Optional<StudentModel> s = this.service.getById(id);
        if (s.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        s.get().setApproved(approved);
        return new ResponseEntity<>(this.mapper.toDto(this.service.save(s.get())), HttpStatus.OK);
    }

}
