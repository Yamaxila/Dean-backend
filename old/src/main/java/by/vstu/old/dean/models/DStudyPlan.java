package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "DuchPlan", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "duchplan_id", nullable = false))
})
public class DStudyPlan extends OldDBBaseModel {

    @Formula("(select Dgrup.dgrup_id from Dgrup where Dgrup.gr = gr)")
    private Long groupId;

//    @Column(name = "Npotoka")
//    private Integer streamNumber;

    @Column(name = "god_obraz")
    private Integer year;

//    @Column(name = "Npp")
//    private Integer listNumber;

    @Column(name = "god_uch1")
    private Integer yearStart;

    @Column(name = "god_uch2")
    private Integer yearEnd;

    @Size(max = 8)
    @NotNull
    @Column(name = "semestr_uch", nullable = false, length = 8)
    private String semester;

    @NotNull
    @Column(name = "N_semestra", nullable = false)
    private Integer semesterNumber;

    @Column(name = "kurs")
    private Integer course;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "nekz", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private DExamModel exam;

    @NotNull
    @Column(name = "ndis", nullable = false)
    private Integer ndis;

    @Column(name = "clock", precision = 18, scale = 2)
    private BigDecimal time;

    @Column(name = "zach_ed", precision = 18, scale = 2)
    private BigDecimal testType;

    @Column(name = "zach_ed_vip", precision = 18, scale = 2)
    private BigDecimal testTypeEnd;

    @Column(name = "nved")
    private Integer planNumber;

    @Column(name = "nved_dek")
    private Integer planNumberDean;

    @Column(name = "data_ved")
    private Instant planDate;

    @Column(name = "nved2")
    private Integer planNumber2;

    @Column(name = "nprep")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long teacherId;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "n")
    private Integer n;

    @Column(name = "priz_vip")
    private Integer endType;

    @Column(name = "clock_vip", precision = 18, scale = 2)
    private BigDecimal endTime;

    @Column(name = "kontr1")
    private Integer kontr1;

    @Column(name = "kontr2")
    private Integer kontr2;

    @Column(name = "kontr3")
    private Integer kontr3;

    @Column(name = "kontr4")
    private Integer kontr4;

    @Column(name = "kontr5")
    private Integer kontr5;

    @Size(max = 10)
    @Column(name = "kr", length = 10)
    private String kr;

    @Column(name = "kurs_rab")
    private Integer kursRab;

    @Column(name = "kurs_proekt")
    private Integer kursProekt;

    @Column(name = "ras_graf")
    private Integer rasGraf;

    @Column(name = "lek")
    private Integer lectureTime;

    @Column(name = "lab")
    private Integer labTime;

    @Column(name = "prak")
    private Integer practiceTime;

    @Column(name = "vsego")
    private Integer timeSum;

    @Column(name = "integ_mod")
    private Integer integMod;

    @Size(max = 7)
    @Column(name = "zachteno", length = 7)
    private String zachteno;

    @Column(name = "N_dis_sem_itg")
    private Integer nDisSemItg;

    @Column(name = "kol_dis")
    private Integer kolDis;

}