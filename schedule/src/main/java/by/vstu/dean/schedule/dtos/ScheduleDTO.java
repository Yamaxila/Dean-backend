package by.vstu.dean.schedule.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.timetable.models.LessonModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO extends PublicDTO {

    private String lessonDay;
    private Boolean numerator;
    private Integer weekNumber;
    @ReflectionField(value = "lessonNumber", clazz = LessonModel.class)
    private Integer lessonNumber;

    private String subGroup;
    private String frame;
    private String location;
    private String disciplineName;
    private String typeClassName;
    private V1GroupDTO group;
    private V1TeacherDTO teacher;
    private String departmentTeacherDisplayName;
    private Long departmentId;
    private boolean correspondence;
    private LocalDate startDate;
    private LocalDate endDate;
}
