package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Dkvalif", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dkvalif_id", nullable = false))
})
public class DQualificationModel extends OldDBBaseModel {
    @Column(name = "namekvalif", length = 50)
    private String namePart1;

    @Column(name = "namekvalif_1", length = 50)
    private String namePart2;

}