package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Класс, представляющий объект предыдущего места учебы.
 */
@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EducationModel extends DBBaseModel {

    /**
     * Студент, связанный с этим предыдущим местом учебы.
     */
    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(notes = "Студент")
    private StudentModel student;

    /**
     * Учреждение образования.
     */
    @ApiModelProperty(notes = "Учреждение образования")
    private String education;

    /**
     * Тип документа об образовании.
     */
    @ApiModelProperty(notes = "Тип документа об образовании")
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    @ApiModelProperty(notes = "Серия документа об образовании")
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    @ApiModelProperty(notes = "Номер документа об образовании")
    private String educationDocumentNumber;

}
