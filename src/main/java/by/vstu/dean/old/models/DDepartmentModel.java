package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Dkaf", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dkaf_id", nullable = false))
})
public class DDepartmentModel extends OldDBBaseModel {
    @Column(name = "kaf", length = 100)
    private String name;

    @Column(name = "kaf_kratko", length = 20)
    private String shortName;

    @Column(name = "aydit", length = 10)
    private String room;

    @JoinColumn(name = "dfak_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DFacultyModel faculty;

}