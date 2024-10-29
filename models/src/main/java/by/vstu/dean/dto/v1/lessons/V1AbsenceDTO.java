package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.lessons.AbsenceModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * DTO for {@link AbsenceModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Отработки")
public class V1AbsenceDTO extends BaseDTO {
    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate date;

    @NotNull
    private V1DisciplineDTO discipline;

    @NotNull
    private V1DepartmentDTO department;

    @NotNull
    private V1TeacherDTO teacher;

    @NotNull
    @ReflectionField(clazz = AbsenceModel.class)
    private ELessonType lessonType;

    @NotNull
    @ReflectionField(clazz = AbsenceModel.class)
    private Integer lessonNumber;

    @NotNull
    private V1StudentDTO student;

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
