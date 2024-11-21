package by.vstu.dean.schedule.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.dto.v1.pub.teachers.V1PublicTeacherDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
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

    private boolean correspondence;
    private V1GroupDTO group;
    private V1PublicTeacherDTO teacher;
    //ToDO: скорее всего будет удалено
//    private Boolean numerator;
//    private Integer weekNumber;
    @ReflectionField(value = "weekType.name", clazz = LessonModel.class)
    private String weekType;
    @ReflectionField(value = "day", clazz = LessonModel.class)
    private Short lessonDay;
    @ReflectionField(value = "subGroup.name", clazz = LessonModel.class)
    private String subGroup;
    @ReflectionField(value = "lessonNumber", clazz = LessonModel.class)
    private Short lessonNumber;
    @ReflectionField(value = "room.frame.id", clazz = LessonModel.class)
    private int frame;
    @ReflectionField(value = "room.roomNumber", clazz = LessonModel.class)
    private String location;
    @ReflectionField(value = "discipline.name", clazz = LessonModel.class)
    private String disciplineName;
    @ReflectionField(value = "lessonType.name", clazz = LessonModel.class)
    private String typeClassName;
    @ReflectionField(value = "startDate", clazz = LessonModel.class)
    private LocalDate startDate;
    @ReflectionField(value = "endDate", clazz = LessonModel.class)
    private LocalDate endDate;
}
