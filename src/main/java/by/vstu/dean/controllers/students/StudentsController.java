package by.vstu.dean.controllers.students;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.BaseController;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SecurityDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/students")
@Api(tags = "Students", description = "Студенты")
public class StudentsController extends BaseController<StudentModel, StudentModelRepository, StudentService> {

    public StudentsController(StudentService service) {
        super(service);
    }

    @RequestMapping(value="/",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/getAllByGroup",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllByGroup", notes = "Отправляет все объекты из базы по id группы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllByGroup(@RequestParam Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id), HttpStatus.OK);
    }

    @RequestMapping(value="/",
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
