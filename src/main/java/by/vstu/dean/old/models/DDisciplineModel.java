package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "Ddiscip", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ndis", nullable = false))
})
public class DDisciplineModel extends OldDBBaseModel {
    @Size(max = 120)
    @Column(name = "namedis", length = 120)
    private String name;

    @Size(max = 30)
    @Column(name = "namedis1", length = 30)
    private String shortName;

    @Column(name = "priz")
    private Integer priz;

    @Column(name = "dkaf_id")
    private Integer dkafId;

}