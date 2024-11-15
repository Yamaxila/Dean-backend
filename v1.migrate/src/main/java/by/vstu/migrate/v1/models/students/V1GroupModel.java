package by.vstu.migrate.v1.models.students;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.enums.V1ESemester;
import by.vstu.migrate.v1.models.V1FacultyModel;
import by.vstu.migrate.v1.models.specs.V1SpecialityModel;
import com.google.gson.annotations.JsonAdapter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Класс, представляющий объект группы.
 */
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class V1GroupModel extends V1DBBaseModel {

    /**
     * Название группы.
     */
    private String name;

    /**
     * Специальность, связанная с данной группой.
     */
    @ManyToOne
    @JoinColumn(name = "spec_id")
    private V1SpecialityModel spec;

    /**
     * Факультет, связанный с данной группой.
     */
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private V1FacultyModel faculty;

    /**
     * Год поступления группы.
     */
    private Integer yearStart;

    /**
     * Семестр окончания
     */
    @Enumerated(EnumType.ORDINAL)
    private V1ESemester endSemester;

    /**
     * Год окончания обучения группы.
     */
    private Integer yearEnd;

    /**
     * Дата начала обучения группы.
     */
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate dateStart;

    /**
     * Дата окончания обучения группы.
     */
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate dateEnd;

    /**
     * Текущий курс группы.
     */
    private Integer currentCourse;

    /**
     * Средний балл студентов в группе.
     */
    private Double score;

}
