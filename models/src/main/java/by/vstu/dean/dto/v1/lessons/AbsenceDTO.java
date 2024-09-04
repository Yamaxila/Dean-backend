package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.lessons.AbsenceModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * DTO for {@link AbsenceModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AbsenceDTO extends BaseDTO {
    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate date;

    @NotNull
    private DisciplineDTO discipline;

    @NotNull
    private DepartmentDTO department;

    @NotNull
    private TeacherDTO teacher;

    @NotNull
    @ReflectionField(clazz = AbsenceModel.class)
    private ELessonType lessonType;

    @NotNull
    @ReflectionField(clazz = AbsenceModel.class)
    private Integer lessonNumber;

    @NotNull
    private StudentDTO student;

    @NotNull
    @ReflectionField(clazz = AbsenceModel.class)
    private Double absenceTime;

    @NotNull
    @ReflectionField(clazz = AbsenceModel.class)
    private Double hourPrice;

    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate paymentDate;

    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate dateCompleted;

    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate datePrint;

    @ReflectionField(clazz = AbsenceModel.class)
    private String reasonMsg;

    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate dateErip;

    @ReflectionField(clazz = AbsenceModel.class)
    private boolean printed;
}
