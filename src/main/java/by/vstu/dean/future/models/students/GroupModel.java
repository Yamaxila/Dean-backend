package by.vstu.dean.future.models.students;

import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.models.specs.SpecialityModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
