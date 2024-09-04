package by.vstu.dean.models;

import by.vstu.dean.enums.ExamType;
import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dekzam", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "nekz", nullable = false))
})
public class DExamModel extends OldDBBaseModel {
    @Size(max = 20)
    @Column(name = "nameekzam", length = 20)
    private String name;

    @Size(max = 8)
    @Column(name = "vidoc", length = 8)
    private String type;


    public ExamType getExamType() {

        if (type.equalsIgnoreCase("зачет"))
            return ExamType.TEST;

        if (type.equalsIgnoreCase("экзамен"))
            return ExamType.EXAM;

        return ExamType.UNKNOWN;
    }
}