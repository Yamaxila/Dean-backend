package by.vstu.dean.models.students.internal;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.students.StudentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dean_parents")
@Schema(title = "Модель родителя/опекуна студента")
public class ParentModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    private StudentModel student;

    private String surname;
    private String name;
    private String patronymic;
    private String job;

    @JoinColumn(name = "phone_id")
    @OneToOne
    private PhoneModel phone;


}
