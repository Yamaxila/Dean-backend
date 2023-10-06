package by.vstu.dean.future.models.students;

import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.models.specs.SpecialityModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
@ApiModel(description = "Объект группы")
public class GroupModel extends DBBaseModel {
    @ApiModelProperty(notes = "Группа")
    private String name;

    @ManyToOne
    @JoinColumn(name = "spec_id")
    @ApiModelProperty(notes = "Специальность")
    private SpecialityModel spec;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @ApiModelProperty(notes = "Факультет")
    private FacultyModel faculty;

    @ApiModelProperty(notes = "Год поступления")
    private Integer yearStart;
    @ApiModelProperty(notes = "Год окончания")
    private Integer yearEnd;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата поступления???") //FIXME
    private LocalDate dateStart;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата окончания")
    private LocalDate dateEnd;

    @ApiModelProperty(notes = "Средний балл")
    private Double score;

}
