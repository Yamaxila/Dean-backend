package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Dmetodist", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dmetodist_id", nullable = false))
})
public class DMethodistModel extends OldDBBaseModel {
    @JoinColumn(name = "dfak_id")
    @ManyToOne
    private DFacultyModel dfakId;

    @Column(name = "metodist", nullable = false, length = 30)
    private String methodistName;

    @Column(name = "god_uch1")
    private Integer yearStart;

    @Column(name = "god_uch2")
    private Integer yearEnd;

    @Column(name = "semestr_uch", length = 8)
    private String semestr;

    @Column(name = "priz_otchis")
    private Integer expelledType;

    @Column(name = "priz_rasp")
    private Integer expelledRasp; //FIXME: Rasp???

}