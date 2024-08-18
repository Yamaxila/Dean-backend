package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DYO", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dyo_id", nullable = false))
})
public class DInstitutionModel extends OldDBBaseModel {

    @Column(name = "nameYO", length = 160)
    private String fullName;

    @Column(name = "nameYO1", length = 30)
    private String shortName;

    @Column(name = "priz")
    private Integer status;

}