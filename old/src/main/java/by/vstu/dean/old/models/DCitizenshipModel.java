package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dgraj", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dgraj_id", nullable = false))
})
public class DCitizenshipModel extends OldDBBaseModel {
    @Column(name = "graj", length = 30)
    private String name;

}