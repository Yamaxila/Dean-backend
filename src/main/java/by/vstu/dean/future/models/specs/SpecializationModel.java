package by.vstu.dean.future.models.specs;

import by.vstu.dean.future.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specializations")
@ApiModel(description = "Объект специализации")
public class SpecializationModel extends DBBaseModel {

    @ApiModelProperty(notes = "Название специализации")
    private String name;
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;
    @ApiModelProperty(notes = "Код специальности")
    private String spezCode;

    @JoinColumn(name = "spec_id")
    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(notes = "Специальность")
    private SpecialityModel spec;

    @JoinColumn(name = "qualification_id")
    @ManyToOne
    @ApiModelProperty(notes = "Квалификация")
    private QualificationModel qualification;

}
