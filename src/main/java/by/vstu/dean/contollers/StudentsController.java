package by.vstu.dean.contollers;

import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/students")
public class StudentsController {

    @Autowired
    private StudentService service;

    @GetMapping("/getAll")
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<List<StudentModel>> getAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllByGroup")
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<List<StudentModel>> getAllByGroup(@RequestParam Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id), HttpStatus.OK);
    }

    @GetMapping("/getById")
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<StudentModel> getById(@RequestParam Long id) {
        return new ResponseEntity<>(this.service.findOne(id), HttpStatus.OK);
    }
}
