package by.vstu.migrate.v1.models.students;

import by.vstu.migrate.v1.V1DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий объект предыдущего места учебы.
 */
@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class V1EducationModel extends V1DBBaseModel {

    /**
     * Студент, связанный с этим предыдущим местом учебы.
     */
    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    private V1StudentModel student;

    /**
     * Учреждение образования.
     */
    private String education;

    /**
     * Тип документа об образовании.
     */
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    private String educationDocumentNumber;

}
