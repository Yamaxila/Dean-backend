package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.students.dtos.StudentGradeAvgDTO;
import by.vstu.dean.students.dtos.StudentGradeDTO;
import by.vstu.dean.students.services.StudentGradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_GROUP_ELDER')")
@RequiredArgsConstructor
@Tag(name = "StudentGrade", description = "Оценки студента")
public class V1StudentGradeController {
    private final StudentGradeService studentGradeService;

    /**
     * Получение оценок сессии.
     *
     * @param semester номер семестра
     * @return Оценки
     */
    @Operation(method = "session", description = "Получение оценок сессии")
    @RequestMapping(value = "/session",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<StudentGradeAvgDTO> getGradesSession(@RequestParam(required = false) Integer semester) {
        List<Integer> semesters = this.studentGradeService.getStudentGradesSession(null).stream().map(StudentGradeDTO::getSemesterNumber).toList();
        List<StudentGradeDTO> studentGradeDTOS = this.studentGradeService.getStudentGradesSession(semester);
        Double averageGrade = studentGradeDTOS.stream().filter(s -> s.getGrade().matches("\\d"))
                .mapToInt(s -> Integer.parseInt(s.getGrade())).average().orElse(0.0);
        return new ResponseEntity<>(new StudentGradeAvgDTO(semesters, averageGrade, studentGradeDTOS), HttpStatus.OK);
    }


}
