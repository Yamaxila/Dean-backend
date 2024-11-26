package by.vstu.dean.students.controllers.v1;

import by.vstu.dean.students.dtos.StudentAbsenceMonthDTO;
import by.vstu.dean.students.services.StudentAbsenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/absences")
@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_GROUP_ELDER')")
@RequiredArgsConstructor
@Tag(name = "StudentAbsence", description = "Отработки студента")
public class V1StudentAbsenceController {

    private final StudentAbsenceService studentAbsenceService;

    /**
     * Получение отработок студента по месяцам.
     *
     * @return Отработки
     */
    @Operation(description = "Получение отработок студента по месяцам")
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<StudentAbsenceMonthDTO>> getAbsences(@AuthenticationPrincipal Jwt principal) {
        return new ResponseEntity<>(this.studentAbsenceService.findAbsencesByStudent(principal.getClaim("id_from_source")), HttpStatus.OK);
    }
}
