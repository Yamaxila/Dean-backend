package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.students.dtos.StudentGradeAvgDTO;
import by.vstu.dean.students.dtos.StudentGradeDTO;
import by.vstu.dean.students.services.StudentGradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/student/grades")
@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_GROUP_ELDER')")
public class V1StudentGradeController {
    private final StudentGradeService studentGradeService;

    public V1StudentGradeController(StudentGradeService studentGradeService) {
        this.studentGradeService = studentGradeService;
    }

    @RequestMapping(value = "/session",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<?> getGradesSession(@RequestParam(required = false) Integer semester) {
        List<StudentGradeDTO> studentGradeDTOS = this.studentGradeService.getStudentGradesSession(semester);
        Double averageGrade = studentGradeDTOS.stream().filter(s -> s.getGrade().matches("\\d"))
                .mapToInt(s -> Integer.parseInt(s.getGrade())).average().orElse(0.0);
        return new ResponseEntity<>(new StudentGradeAvgDTO(averageGrade, studentGradeDTOS), HttpStatus.OK);
    }


}
