package by.vstu.dean.future.models.specs;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialities")
@ApiModel(description = "Объект специальности")
public class SpecialityModel extends DBBaseModel {

    @NotNull
    @ApiModelProperty(notes = "Название специальности")
    private String name;
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;
    @ApiModelProperty(notes = "Код специальности")
    private String specCode;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @ApiModelProperty(notes = "Кафедра, привязанная к специальности")
//    @JsonIgnore
    private DepartmentModel department;

}
