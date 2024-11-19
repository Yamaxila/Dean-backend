package by.vstu.dean.timetable.models;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.timetable.enums.ESubGroup;
import by.vstu.dean.timetable.enums.EWeekType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Модель объекта занятия.
 */
@Entity
@Table(name = "timetable_lessons")
@Getter
@Setter
@Schema(title = "Модель объекта занятия")
public class LessonModel extends DBBaseModel {

    /**
     * Группа.
     */
    @JoinColumn(name = "group_id")
    @ManyToOne
    @Schema(title = "Группа")
    private GroupModel group;

    /**
     * Преподаватель.
     */
    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @Schema(title = "Преподаватель")
    private TeacherModel teacher;

    /**
     * Дисциплина.
     */
    @JoinColumn(name = "discipline_id")
    @ManyToOne
    @Schema(title = "Дисциплина")
    private DisciplineModel discipline;

    /**
     * Аудитория.
     */
    @JoinColumn(name = "room_id")
    @ManyToOne
    @Schema(title = "Аудитория")
    private ClassroomModel room;

    /**
     * Номер пары (1-7).
     */
    @Schema(title = "Номер пары (1-7)")
    private Short lessonNumber;

    /**
     * Номер дня недели (0-6).
     */
    @Schema(title = "Номер дня недели (0-6)")
    private Short day;

    /**
     * Тип занятия.
     */
    @Schema(title = "Тип занятия")
    private ELessonType lessonType;

    /**
     * Тип недели.
     */
    @Schema(title = "Тип недели")
    private EWeekType weekType;

    /**
     * Подгруппа.
     */
    @Schema(title = "Подгруппа")
    private ESubGroup subGroup;

    /**
     * Дата начала занятия.
     */
    @Schema(title = "Дата начала занятия")
    private LocalDate startDate;

    /**
     * Дата конца занятия.
     */
    @Schema(title = "Дата конца занятия")
    private LocalDate endDate;

    /**
     * Флаг видимости занятия в сервисе расписания.
     */
    @Schema(title = "Флаг видимости занятия в сервисе расписания")
    private boolean visible;

}
