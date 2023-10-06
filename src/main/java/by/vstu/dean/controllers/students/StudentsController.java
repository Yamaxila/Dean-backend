package by.vstu.dean.controllers.students;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с объектами студентов.
 */
@RestController
@RequestMapping("api/students")
@Api(tags = "Students", description = "Студенты")
public class StudentsController extends BaseController<StudentModel, StudentModelRepository, StudentService> {

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
            method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    /**
     * Получить всех студентов по идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return Список студентов
     */
    @RequestMapping(value = "/byGroup",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllByGroup", notes = "Отправляет все объекты из базы по id группы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllByGroup(@RequestParam Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id), HttpStatus.OK);
    }

    /**
     * Получить студента по идентификатору.
     *
     * @param id Идентификатор студента
     * @return Объект студента
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getById", notes = "Отправляет объект из базы по id")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<StudentModel> getById(@RequestParam Long id) {
        return super.getById(id);
    }
}
