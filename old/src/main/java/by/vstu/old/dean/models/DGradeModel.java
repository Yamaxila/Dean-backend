package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Docenka", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "docenka_id", nullable = false))
})
public class DGradeModel extends OldDBBaseModel {

    @Column(name = "ball")
    private Integer range;

    @Column(name = "ocenka_sim")
    private String grade;

    @Column(name = "vidoc")
    private String examType;

}
