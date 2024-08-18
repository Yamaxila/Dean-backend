package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;

import javax.persistence.*;

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