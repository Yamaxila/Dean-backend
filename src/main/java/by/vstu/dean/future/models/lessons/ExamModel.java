package by.vstu.dean.future.models.lessons;

import by.vstu.dean.enums.ExamType;
import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="exams")
@Data
public class ExamModel extends DBBaseModel {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private ExamType type;

}
