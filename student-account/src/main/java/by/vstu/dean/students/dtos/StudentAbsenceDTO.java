package by.vstu.dean.students.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.lessons.AbsenceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAbsenceDTO extends PublicDTO {
    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate date;
    @ReflectionField(value = "discipline.name", clazz = AbsenceModel.class)
    private String discipline;
    @ReflectionField(clazz = AbsenceModel.class)
    private ELessonType lessonType;
    @ReflectionField(clazz = AbsenceModel.class)
    private Integer lessonNumber;
    private String teacher;
    @ReflectionField(clazz = AbsenceModel.class)
    private Double absenceTime;
    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate dateCompleted;
    @ReflectionField(clazz = AbsenceModel.class)
    private String reasonMsg;
    @ReflectionField(clazz = AbsenceModel.class)
    private boolean printed;
}
