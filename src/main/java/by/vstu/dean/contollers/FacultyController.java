package by.vstu.dean.contollers;

import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.dean.services.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @GetMapping("/getAll")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<List<FacultyModel>> getAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }
}
