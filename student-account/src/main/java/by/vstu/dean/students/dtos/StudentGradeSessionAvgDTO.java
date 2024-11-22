package by.vstu.dean.students.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * DTO Средней оценки.
 */
@Getter
@AllArgsConstructor
@Schema(title = "DTO Средней оценки")
public class StudentGradeSessionAvgDTO {

    /**
     * Список семестров.
     */
    @Schema(title = "Список семестров")
    private List<Integer> semesters;

    /**
     * Средняя оценка.
     */
    @Schema(title = "Средняя оценка")
    private Double average;

    /**
     * Список оценок.
     */
    @Schema(title = "Список оценок")
    private List<StudentGradeSessionDTO> grades;
}
