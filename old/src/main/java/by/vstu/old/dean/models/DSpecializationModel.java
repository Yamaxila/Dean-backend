package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Getter
@Setter
@Entity
@Table(name = "Dspecializ", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dspecializ_id", nullable = false))
})
public class DSpecializationModel extends OldDBBaseModel {
    @JoinColumn(name = "dspec_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    private DSpecialityModel speciality;

    @Column(name = "namespecializ", length = 120)
    private String name;

    @JoinColumn(name = "dkvalif_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DQualificationModel dkvalifId;

    @Column(name = "namespecializ_sam", length = 120)
    private String nameSpezSam;

    @Column(name = "specializ_kratko", length = 10)
    private String shortName;

}