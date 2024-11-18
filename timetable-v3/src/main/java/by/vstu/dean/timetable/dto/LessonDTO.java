package by.vstu.dean.timetable.dto;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.lessons.V1DisciplineDTO;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.dto.v1.rooms.V1ClassroomDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.timetable.enums.ESubGroup;
import by.vstu.dean.timetable.enums.EWeekType;
import by.vstu.dean.timetable.models.LessonModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class LessonDTO extends BaseDTO {

    private V1GroupDTO group;

    private V1TeacherDTO teacher;

    private V1DisciplineDTO discipline;

    private V1ClassroomDTO room;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private Short lessonNumber;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private Short day;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private ELessonType lessonType;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private EWeekType weekType;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private ESubGroup subGroup;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private LocalDate startDate;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private LocalDate endDate;

    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private boolean visible;
}
