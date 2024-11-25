package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.students.dtos.StudentGradeSessionAvgDTO;
import by.vstu.dean.students.dtos.StudentGradeSessionDTO;
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
import java.util.Objects;


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
    public ResponseEntity<StudentGradeSessionAvgDTO> getGradesSession(@RequestParam(required = false) Integer semester) {
        List<StudentGradeSessionDTO> studentGradeSessionDTOS = this.studentGradeService.getStudentGradesSession();
        List<Integer> semesters = studentGradeSessionDTOS.stream().map(StudentGradeSessionDTO::getSemesterNumber).distinct().toList();

        studentGradeSessionDTOS = studentGradeSessionDTOS.stream().filter(s -> semester == null || Objects.equals(s.getSemesterNumber(), semester)).toList();
        double averageGrade = studentGradeSessionDTOS.stream().filter(s -> s.getGrade().matches("\\d"))
                .mapToInt(s -> Integer.parseInt(s.getGrade())).average().orElse(0.0);
        return new ResponseEntity<>(new StudentGradeSessionAvgDTO(semesters, averageGrade, studentGradeSessionDTOS), HttpStatus.OK);
    }
}
