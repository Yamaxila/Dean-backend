package by.vstu.dean.models.specs;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Модель объекта квалификации.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qualifications")
@ApiModel(description = "Объект квалификации")
public class QualificationModel extends DBBaseModel {

    /**
     * Название квалификации.
     */
    @NotNull
    @ApiModelProperty(notes = "Название квалификации")
    private String name;

    /**
     * Название квалификации в родительном падеже.
     */
    @NotNull
    @ApiModelProperty(notes = "Название квалификации в родительном падеже")
    private String nameGenitive;

}
