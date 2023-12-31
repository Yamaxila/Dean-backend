package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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