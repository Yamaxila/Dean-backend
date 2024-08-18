package by.vstu.dean.models.students;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.enums.ESemester;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.models.specs.SpecialityModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
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
public class GroupModel extends DBBaseModel {

    /**
     * Название группы.
     */
    @ApiModelProperty(notes = "Группа")
    private String name;

    /**
     * Специальность, связанная с данной группой.
     */
    @ManyToOne
    @JoinColumn(name = "spec_id")
    @ApiModelProperty(notes = "Специальность")
    private SpecialityModel spec;

    /**
     * Факультет, связанный с данной группой.
     */
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @ApiModelProperty(notes = "Факультет")
    private FacultyModel faculty;

    /**
     * Год поступления группы.
     */
    @ApiModelProperty(notes = "Год поступления")
    private Integer yearStart;

    /**
     * Семестр окончания
     */
    @Enumerated(EnumType.ORDINAL)
    private ESemester endSemester;

    /**
     * Год окончания обучения группы.
     */
    @ApiModelProperty(notes = "Год окончания")
    private Integer yearEnd;

    /**
     * Дата начала обучения группы.
     */
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата поступления???") //FIXME
    private LocalDate dateStart;

    /**
     * Дата окончания обучения группы.
     */
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата окончания")
    private LocalDate dateEnd;

    /**
     * Средний балл студентов в группе.
     */
    @ApiModelProperty(notes = "Средний балл")
    private Double score;

}
