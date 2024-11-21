package by.vstu.dean.students.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * DTO Средней оценки.
 */
@Getter
@AllArgsConstructor
@Schema(title = "DTO Средней оценки")
public class StudentGradeAvgDTO {

    /**
     * Средняя оценка.
     */
    @Schema(title = "Средняя оценка")
    private Double average;

    /**
     * Список оценок.
     */
    @Schema(title = "Список оценок")
    private List<StudentGradeDTO> grades;
}
