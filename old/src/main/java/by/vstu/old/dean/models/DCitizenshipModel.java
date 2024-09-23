package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
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