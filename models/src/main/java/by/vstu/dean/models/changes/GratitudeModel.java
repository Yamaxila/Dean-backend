package by.vstu.dean.models.changes;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.students.StudentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dean_gratitude")
@Schema(title = "Модель благодарности студенту")
public class GratitudeModel extends DBBaseModel {


    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Schema(title = "Студент")
    private StudentModel student;

    private String orderNumber;
    private String orderText;

}
