package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.students.dtos.StudentAbsenceMonthDTO;
import by.vstu.dean.students.services.StudentAbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/absences")
@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_GROUP_ELDER')")
@RequiredArgsConstructor
public class V1StudentAbsenceController {

    private final StudentAbsenceService studentAbsenceService;

    @RequestMapping(value = "",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<StudentAbsenceMonthDTO>> getAbsences() {
        return new ResponseEntity<>(this.studentAbsenceService.findAbsencesByStudent(), HttpStatus.OK);
    }
}
