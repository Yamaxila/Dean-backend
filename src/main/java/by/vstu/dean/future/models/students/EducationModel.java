package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Объект предыдущего места учебы")
public class EducationModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(notes = "Студент")
    private StudentModel student;

    @ApiModelProperty(notes = "Учреждение образования")
    private String education;
    @ApiModelProperty(notes = "Тип документа об образовании")
    private String educationDocumentType;
    @ApiModelProperty(notes = "Серия документа об образовании")
    private String educationDocumentSerial;
    @ApiModelProperty(notes = "Номер документа об образовании")
    private String educationDocumentNumber;

}
