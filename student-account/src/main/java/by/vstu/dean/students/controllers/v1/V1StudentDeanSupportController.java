package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.students.dtos.StudentAbsenceDTO;
import by.vstu.dean.students.dtos.StudentGradeSessionAvgDTO;
import by.vstu.dean.students.dtos.StudentGradeSessionDTO;
import by.vstu.dean.students.services.StudentAbsenceService;
import by.vstu.dean.students.services.StudentGradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/dean/support")
@PreAuthorize("hasAnyAuthority('ROLE_METHODIST', 'ROLE_SERVICE')")
@Tag(name = "StudentDeanSupportController", description = "Данные студента для деканата")
@RequiredArgsConstructor
public class V1StudentDeanSupportController {

    private final StudentGradeService studentGradeService;
    private final StudentAbsenceService studentAbsenceService;

    /**
     * Получение оценок сессии.
     *
     * @param caseNo номер зачетной книжки.
     * @return Оценки
     */
    @Operation(method = "session", description = "Получение оценок сессии")
    @RequestMapping(value = "/grades/session/{caseNo}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<StudentGradeSessionAvgDTO> getGradesSession(@PathVariable Long caseNo) {
        List<StudentGradeSessionDTO> studentGradeSessionDTOS = this.studentGradeService.getStudentGradesSession(caseNo);

        double averageGrade = studentGradeSessionDTOS.stream().filter(s -> s.getGrade().matches("\\d"))
                .mapToInt(s -> Integer.parseInt(s.getGrade())).average().orElse(0.0);
        return new ResponseEntity<>(new StudentGradeSessionAvgDTO(null, averageGrade, studentGradeSessionDTOS), HttpStatus.OK);
    }

    /**
     * Получение отработок студента по месяцам.
     *
     * @param caseNo номер зачетной книжки.
     * @return Отработки
     */
    @Operation(description = "Получение отработок студента")
    @RequestMapping(value = "/absences/{caseNo}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<StudentAbsenceDTO>> getAbsences(@PathVariable Long caseNo) {
        return new ResponseEntity<>(this.studentAbsenceService.findAbsencesByStudentCaseNo(caseNo), HttpStatus.OK);
    }
}
