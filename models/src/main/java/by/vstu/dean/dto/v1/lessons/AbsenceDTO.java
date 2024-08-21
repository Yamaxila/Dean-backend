package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.enums.ELessonType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO for {@link by.vstu.dean.models.lessons.AbsenceModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AbsenceDTO extends BaseDTO {

    private LocalDate date;

    @NotNull
    private DisciplineDTO discipline;

    @NotNull
    private DepartmentDTO department;

    @NotNull
    private TeacherDTO teacher;

    @NotNull
    private ELessonType lessonType;

    @NotNull
    private Integer lessonNumber;

    @NotNull
    private StudentDTO student;

    @NotNull
    private Double absenceTime;

    @NotNull
    private Double hourPrice;

    private LocalDate paymentDate;

    private LocalDate dateCompleted;

    private LocalDate datePrint;

    private String reasonMsg;

    private LocalDate dateErip;

    private boolean printed;
}
