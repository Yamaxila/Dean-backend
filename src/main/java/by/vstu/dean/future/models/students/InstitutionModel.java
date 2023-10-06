package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "institutions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект учреждения образования")
public class InstitutionModel extends DBBaseModel {
    @ApiModelProperty(notes = "Полное название")
    private String fullName;
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

}
