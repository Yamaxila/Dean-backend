package by.vstu.dean.timetable.models;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.timetable.enums.ESubGroup;
import by.vstu.dean.timetable.enums.EWeekType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "timetable_lessons")
@Getter
@Setter
public class LessonModel extends DBBaseModel {

    @JoinColumn(name = "group_id")
    @ManyToOne
    private GroupModel group;

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    private TeacherModel teacher;

    @JoinColumn(name = "discipline_id")
    @ManyToOne
    private DisciplineModel discipline;

    @JoinColumn(name = "room_id")
    @ManyToOne
    private ClassroomModel room;

    private Short lessonNumber;

    private Short day;

    private ELessonType lessonType;

    private EWeekType weekType;

    private ESubGroup subGroup;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean visible;

}
