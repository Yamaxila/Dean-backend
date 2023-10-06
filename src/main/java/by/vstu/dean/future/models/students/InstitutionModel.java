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

/**
 * Класс, представляющий объект учреждения образования.
 */
@Entity
@Table(name = "institutions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект учреждения образования")
public class InstitutionModel extends DBBaseModel {

    /**
     * Полное название учреждения образования.
     */
    @ApiModelProperty(notes = "Полное название")
    private String fullName;

    /**
     * Краткое название учреждения образования.
     */
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

}
