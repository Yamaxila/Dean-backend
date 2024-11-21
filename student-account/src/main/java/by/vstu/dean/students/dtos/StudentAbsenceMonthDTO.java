package by.vstu.dean.students.dtos;

import by.vstu.dean.models.students.GroupModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;
import java.util.List;

/**
 * DTO Список отработок по месяцам.
 */
@Data
@AllArgsConstructor
@Schema(title = "DTO Список отработок по месяцам")
public class StudentAbsenceMonthDTO {
    /**
     * Год.
     */
    @Schema(title = "Год")
    private Integer year;
    /**
     * Месяц.
     */
    @Schema(title = "Месяц")
    private Month month;
    /**
     * Список отработок.
     */
    @Schema(title = "Список отработок")
    private List<StudentAbsenceDTO> absencesDTos;
}
