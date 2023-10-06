package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
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
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
@ApiModel(description = "Объект дисциплины")
public class DisciplineModel extends DBBaseModel {
    @ApiModelProperty(notes = "Название дисциплины")
    @NotNull
    private String name;
    @ApiModelProperty(notes = "Краткое название дисциплины")
    @NotNull
    private String shortName;
    @JoinColumn(name = "department_id")
    @ManyToOne
    @ApiModelProperty(notes = "Кафедра")
    private DepartmentModel department;

}
