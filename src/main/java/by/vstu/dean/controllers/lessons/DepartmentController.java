package by.vstu.dean.controllers.lessons;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.BaseController;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.future.repo.DepartmentModelRepository;
import by.vstu.dean.services.DepartmentService;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/departments/")
@Api(tags = "Departments", description = "Кафедры")
public class DepartmentController extends BaseController<DepartmentModel, DepartmentModelRepository, DepartmentService> {
    public DepartmentController(DepartmentService service) {
        super(service);
    }


    @RequestMapping(value="/getTeachers",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getTeachers")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<TeacherModel>> getTeachers(@RequestParam Long id) {

        Optional<DepartmentModel> o = this.service.getById(id);

        if(!o.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(o.get().getTeachers().stream().map(TeacherDepartmentMerge::getTeacher).toList(), HttpStatus.OK);
    }

}
