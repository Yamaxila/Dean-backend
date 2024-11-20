package by.vstu.migrate.v1.timetable.models;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.timetable.enums.V1ELessonTypes;
import by.vstu.migrate.v1.timetable.enums.V1ESubGroup;
import by.vstu.migrate.v1.timetable.enums.V1EWeekType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "lessons")
@Setter
@Getter
public class V1LessonModel extends V1DBBaseModel {

    Long groupId;
    Long teacherId;
    Long disciplineId;
    Long roomId;
    Short lessonNumber;
    Short day;
    @Enumerated(EnumType.ORDINAL)
    V1ELessonTypes lessonType;
    @Enumerated(EnumType.ORDINAL)
    V1EWeekType weekType;
    @Enumerated(EnumType.ORDINAL)
    V1ESubGroup subGroup;
    LocalDate startDate;
    LocalDate endDate;
    boolean visible;

}
