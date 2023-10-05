package by.vstu.dean.future.models.specs;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qualifications")
@ApiModel(description = "Объект квалификации")
public class QualificationModel extends DBBaseModel {
    @NotNull
    @ApiModelProperty(notes = "Название квалификации")
    private String name;
    @NotNull
    @ApiModelProperty(notes = "Название квалификации в родительном падеже")
    private String nameGenitive;

}
