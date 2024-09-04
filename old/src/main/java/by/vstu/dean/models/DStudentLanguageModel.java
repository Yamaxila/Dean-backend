package by.vstu.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dinostr", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dinostr_id", nullable = false))
})
public class DStudentLanguageModel extends OldDBBaseModel {
    @Column(name = "inostr", length = 20)
    private String name;

}