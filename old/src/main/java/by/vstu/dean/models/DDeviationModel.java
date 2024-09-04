package by.vstu.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Dotklon", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dotklon_id", nullable = false))
})
public class DDeviationModel extends OldDBBaseModel {
    @JoinColumn(name = "dfak_id")
    @ManyToOne
    private DFacultyModel faculty;

    @Column(name = "delo", nullable = false, length = 8)
    private String document;

    @Column(name = "fam", length = 60)
    private String lastName;

    @Column(name = "gr", length = 8)
    private String groupName;

    @Column(name = "kurs")
    private Integer course;

    @Column(name = "vid_prik", length = 50)
    private String msgType;

    @Column(name = "otchis", length = 30)
    private String expelled;

    @Column(name = "Nprich")
    private Integer reasonNumber;

    @Column(name = "prikaz_otklon", length = 10)
    private String deviationMsg;

    @Column(name = "data_prik")
    private LocalDateTime msgDate;

    @Column(name = "data_s")
    private LocalDateTime dateStart;

    @Column(name = "data_po")
    private LocalDateTime dateEnd;

    @Column(name = "fam_new", length = 30)
    private String lastNameNew;

    @Column(name = "gr_new", length = 8)
    private String groupNameNew;

    @Column(name = "prim")
    private String msg1;

    @Column(name = "prim1")
    private String msg2;

    @Column(name = "tekst_prikaza")
    private String commandMsg1;

    @Column(name = "tekst_prikaza1")
    private String commandMsg;

    @Column(name = "data_likvid")
    private LocalDateTime dateLiquidation;

    @Column(name = "vziskanie")
    private Integer penalty;

}