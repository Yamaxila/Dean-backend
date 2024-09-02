package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dprepod", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "nprep", nullable = false))
})
public class DTeacherModel extends OldDBBaseModel {
    @Size(max = 20)
    @Column(name = "fam", length = 20)
    private String lastName;

    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String firstName;

    @Size(max = 20)
    @Column(name = "otch", length = 20)
    private String secondName;

    @Size(max = 20)
    @Column(name = "uch_zvan", length = 20)
    private String degree;

}