package by.vstu.dean.models.specs;

import by.vstu.dean.core.models.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 * Модель объекта специализации.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specializations")
@ApiModel(description = "Объект специализации")
public class SpecializationModel extends DBBaseModel {

    /**
     * Название специализации.
     */
    @ApiModelProperty(notes = "Название специализации")
    private String name;

    /**
     * Краткое название специализации.
     */
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

    /**
     * Код специализации.
     */
    @ApiModelProperty(notes = "Код специальности")
    private String spezCode;

    /**
     * Специальность.
     */
    @JoinColumn(name = "spec_id")
    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(notes = "Специальность")
    private SpecialityModel spec;

    /**
     * Квалификация.
     */
    @JoinColumn(name = "qualification_id")
    @ManyToOne
    @ApiModelProperty(notes = "Квалификация")
    private QualificationModel qualification;

}
