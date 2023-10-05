package by.vstu.dean.controllers.students;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.BaseController;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups/")
@Api(tags = "Groups")
public class GroupController extends BaseController<GroupModel, GroupModelRepository, GroupService> {

    public GroupController(GroupService service) {
        super(service);
    }

    @RequestMapping(value="/getByName",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getByName")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<GroupModel> getByName(@RequestParam String name) {
        return new ResponseEntity<>(this.service.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(value="/getDepartment",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getDepartment")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<DepartmentModel> getDepartment(@RequestParam Long id) {

        Optional<GroupModel> o = this.service.getById(id);

        if(!o.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        GroupModel group = o.get();

        if(group.getSpec().getDepartment() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(group.getSpec().getDepartment(), HttpStatus.OK);
    }


}
