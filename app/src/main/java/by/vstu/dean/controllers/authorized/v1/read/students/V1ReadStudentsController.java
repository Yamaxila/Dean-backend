package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.core.enums.EStatus;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с объектами студентов.
 */
@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Students", description = "Студенты")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadStudentsController extends BaseReadController<V1StudentDTO, StudentModel, V1StudentMapper, StudentModelRepository, StudentService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис студентов
     */
    public V1ReadStudentsController(StudentService service, V1StudentMapper mapper) {
        super(service, mapper);
    }

    /**
     * Получить всех студентов.
     *
     * @return Список студентов
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<V1StudentDTO>> getAll() {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll()), HttpStatus.OK);
    }

    /**
     * Получить всех студентов по идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return Список студентов
     */
    @RequestMapping(value = "/byGroup/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllByGroup", description = "Отправляет все объекты из базы по id группы")
    public ResponseEntity<List<StudentModel>> getAllByGroup(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id), HttpStatus.OK);
    }

    /**
     * Получить всех активных студентов по идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return Список студентов
     */
    @RequestMapping(value = "/byGroup/{id}/active",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllByGroup", description = "Отправляет все объекты из базы по id группы")
    public ResponseEntity<List<StudentModel>> getAllActiveByGroup(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id).stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE)).toList(), HttpStatus.OK);
    }

    /**
     * Получить студента по идентификатору.
     *
     * @param id Идентификатор студента
     * @return Объект студента
     */
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getById", description = "Отправляет объект из базы по id")
    public ResponseEntity<V1StudentDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    /**
     * Получить всех студентов, нуждающихся в общежитии.
     *
     * @return Список студентов
     */
    @RequestMapping(value = "/homeless",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllHomeless", description = "Отправляет все объекты из базы, которые нуждаются в общежитии")
    public ResponseEntity<List<V1StudentDTO>> getAllHomeless() {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll().stream().filter(s -> s.isNeedHostel() && s.getStatus().equals(EStatus.ACTIVE)).toList()), HttpStatus.OK);
    }

}
