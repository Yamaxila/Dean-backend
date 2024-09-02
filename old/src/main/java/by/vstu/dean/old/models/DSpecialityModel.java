package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Getter
@Setter
@Entity
@Table(name = "Dspec", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dspec_id", nullable = false))
})
public class DSpecialityModel extends OldDBBaseModel {
    @JoinColumn(name = "dfak_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DFacultyModel faculty;

    @Column(name = "shifr_spec", nullable = false, length = 20)
    private String specCode;

    @Column(name = "namespec", length = 120)
    private String name;

    @Column(name = "spec_kratko", length = 6)
    private String short_name;

    @Column(name = "namespec1", length = 30)
    private String namePart1;

    @Column(name = "namespec2", length = 90)
    private String namePart2;

}