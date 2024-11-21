package by.vstu.dean.timetable.dto;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.timetable.enums.ESubGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

/**
 * DTO занятия для front-end.
 */
@Setter
@Getter
@Schema(title = "DTO Занятия для front-end")
public class RoomDTO extends BaseDTO {

    /**
     * Номер аудитории.
     */
    @Nullable
    @Schema(title = "Номер аудитории")
    String roomNumber;

    /**
     * Подгруппа.
     */
    @Nullable
    @Schema(title = "Подгруппа")
    ESubGroup subGroup;

    /**
     * День недели (0-6).
     */
    @Nullable
    @Schema(title = "День недели (0-6)")
    Short day;

    /**
     * Номер пары (1-7).
     */
    @Nullable
    @Schema(title = "Номер пары (1-7)")
    Short lessonNumber;

    /**
     * Учебный корпус.
     */
    @Nullable
    @Schema(title = "Учебный корпус")
    String frame;

    /**
     * Тип аудитории.
     */
    @Nullable
    @Schema(title = "Тип аудитории")
    String roomType;

    /**
     * Тип занятия.
     */
    @Nullable
    @Schema(title = "Тип занятия")
    String lessonType;

    /**
     * Название дисциплины.
     */
    @Nullable
    @Schema(title = "Название дисциплины")
    String disciplineName;

    /**
     * ФИО преподавателя.
     */
    @Nullable
    @Schema(title = "ФИО преподавателя")
    String teacherFullName;

    /**
     * Тип недели.
     */
    @Nullable
    @Schema(title = "Тип недели")
    String weekType;

    /**
     * Название группы.
     */
    @Nullable
    @Schema(title = "Название группы")
    String group;

    /**
     * Идентификатор объекта аудитории.
     */
    @Nullable
    @Schema(title = "Идентификатор объекта аудитории")
    Long roomId;

    /**
     * Идентификатор объекта занятия.
     */
    @Nullable
    @Schema(title = "Идентификатор объекта занятия")
    Long lessonId;

    /**
     * Идентификатор объекта дисциплины.
     */
    @Nullable
    @Schema(title = "Идентификатор объекта дисциплины")
    Long disciplineId;

    /**
     * Идентификатор объекта преподавателя.
     */
    @Nullable
    @Schema(title = "Идентификатор объекта преподавателя")
    Long teacherId;

    /**
     * Идентификатор объекта группы.
     */
    @Nullable
    @Schema(title = "Идентификатор объекта группы")
    Long groupId;

    /**
     * Дата начала занятия.
     */
    @Nullable
    @Schema(title = "Дата начала занятия")
    LocalDate startDate;

    /**
     * Дата конца занятия.
     */
    @Nullable
    @Schema(title = "Дата конца занятия")
    LocalDate endDate;

    /**
     * Флаг видимости занятия в сервисе расписания.
     */
    @Nullable
    @Schema(title = "Флаг видимости занятия в сервисе расписания")
    Boolean visible;

}