package by.vstu.dean.controllers.v1.students;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.dto.mapper.StudentMapper;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Students", description = "Студенты")
public class StudentsController extends BaseController<StudentDTO, StudentModel, StudentMapper, StudentModelRepository, StudentService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис студентов
     */
    public StudentsController(StudentService service) {
        super(service);
    }

    /**
     * Получить всех студентов.
     *
     * @return Список студентов
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentDTO>> getAll() {
        return new ResponseEntity<>(this.service.toDto(this.service.getAll()), HttpStatus.OK);
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
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @ApiOperation(value = "getAllByGroup", notes = "Отправляет все объекты из базы по id группы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
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
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @ApiOperation(value = "getAllByGroup", notes = "Отправляет все объекты из базы по id группы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllActiveByGroup(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id).stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE)).toList(), HttpStatus.OK);
    }

    /**
     * Получить студента по идентификатору.
     *
     * @param id Идентификатор студента
     * @return Объект студента
     */
    @RequestMapping(value = "/{id}/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @ApiOperation(value = "getById", notes = "Отправляет объект из базы по id")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }
}
