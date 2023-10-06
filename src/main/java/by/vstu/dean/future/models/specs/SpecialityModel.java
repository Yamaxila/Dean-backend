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

/**
 * Модель объекта специальности.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialities")
@ApiModel(description = "Объект специальности")
public class SpecialityModel extends DBBaseModel {

    /**
     * Название специальности.
     */
    @NotNull
    @ApiModelProperty(notes = "Название специальности")
    private String name;

    /**
     * Краткое название специальности.
     */
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

    /**
     * Код специальности.
     */
    @ApiModelProperty(notes = "Код специальности")
    private String specCode;

    /**
     * Кафедра, привязанная к специальности.
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @ApiModelProperty(notes = "Кафедра, привязанная к специальности")
    private DepartmentModel department;

}
