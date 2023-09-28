package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    private StudentModel student;

    private String education;
    private String educationDocumentType;
    private String educationDocumentSerial;
    private String educationDocumentNumber;

}
