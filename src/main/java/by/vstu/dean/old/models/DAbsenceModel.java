package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "DPropuski", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dpropuski_id", nullable = false))
})
public class DAbsenceModel extends OldDBBaseModel {
    @Column(name = "god_uch1")
    private Integer yearStart;

    @Column(name = "god_uch2")
    private Integer yearEnd;

    @JoinColumn(name = "dkaf_id")
    @ManyToOne
    private DDepartmentModel department;

    @JoinColumn(name = "ndis")
    @ManyToOne
    private DDisciplineModel discipline;

    @Size(max = 50)
    @Column(name = "vid_nagr", length = 50)
    private String lessonType;

    @Size(max = 8)
    @JoinColumn(name = "delo", referencedColumnName = "delo")
    @ManyToOne(fetch = FetchType.EAGER)
    private DStudentModel student;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "p1")
    private Integer p1;

    @Column(name = "p2")
    private Integer p2;

    @Column(name = "p3")
    private Integer p3;

    @Column(name = "p4")
    private Integer p4;

    @Column(name = "p5")
    private Integer p5;

    @Column(name = "p6")
    private Integer p6;

    @Column(name = "p7")
    private Integer p7;

    @Column(name = "clock", precision = 18, scale = 2)
    private BigDecimal clock;

    @Column(name = "cena", precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "summa", precision = 19, scale = 4)
    private BigDecimal sum;

    @Column(name = "summa_opl", precision = 19, scale = 4)
    private BigDecimal sumPayed;

    @Column(name = "data_opl")
    private LocalDateTime dataOpl;

    @JoinColumn(name = "nprep")
    @ManyToOne
    private DTeacherModel teacher;

    @Size(max = 50)
    @Column(name = "prichina_prop", length = 50)
    private String reasonMsg;

    @Column(name = "npp")
    private Integer number;

    @Column(name = "data_erip")
    private LocalDateTime dateErip;

    @Size(max = 3)
    @Column(name = "otrabotal", length = 3)
    private String completed;

    @Column(name = "data_otrab")
    private LocalDateTime dateCompleted;

    @Column(name = "priz_pech")
    private Integer printed;

    @Column(name = "data_vidachi")
    private LocalDateTime datePrint;

//    @Transient
//    public DStudentModel getLastStudent() {
//        return students.stream().min(Comparator.comparing(OldDBBaseModel::getId)).orElse(null);
//    }
}