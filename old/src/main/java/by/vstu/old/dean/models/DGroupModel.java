package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Dgrup", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dgrup_id", nullable = false))
})
public class DGroupModel extends OldDBBaseModel implements Serializable {
    @JoinColumn(name = "dfak_id", nullable = false)
    @ManyToOne
    private DFacultyModel faculty;

    @JoinColumn(name = "dmetodist_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DMethodistModel methodist;

    @Column(name = "gr", nullable = false, length = 8)
    private String name;

    @Column(name = "god_obraz")
    private Integer yearStart;

    @JoinColumn(name = "dspec_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DSpecialityModel speciality;

    @Column(name = "specializ_kratko", length = 10)
    private String spezShortName;

    @Column(name = "nachkurs")
    private Integer startCourse;

    @Column(name = "kollet")
    private Integer yearsCount;

    @Column(name = "kolmes")
    private Integer kolmes;

    @Column(name = "ball")
    private Integer score;

    @Column(name = "vipusk", length = 3)
    private String expelled;

    @Column(name = "god_vipuska")
    private Integer yearEnd;

    @Column(name = "semestr_vipuska", length = 8)
    private String semestrEnd;

    @Column(name = "prikaz_zach", length = 10)
    private String enrollMsg;

    @Column(name = "data_zach")
    private LocalDateTime enrollDate;

    @Column(name = "data_s")
    private LocalDateTime dateStart;

    @Column(name = "data_po")
    private LocalDateTime dateEnd;

    @Column(name = "priz")
    private Integer marked;

    @Column(name = "kod_sessii")
    private Integer sessionCode;

    @Column(name = "prikaz_zach_bud", length = 10)
    private String enrollMsgFuture;

    @Column(name = "data_zach_bud")
    private LocalDateTime enrollDateFuture;

    @JoinColumn(name = "dkaf_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DDepartmentModel department;

    @Column(name = "spisaydit", length = 100)
    private String spisaydit; // FIXME: Нужно уточнить названия и понять, нужны ли они вообще

    @Column(name = "data1")
    private LocalDateTime date1;

    @Column(name = "data2")
    private LocalDateTime date2;

    @Column(name = "data_stip1")
    private LocalDateTime grantDate1;

    @Column(name = "data_stip2")
    private LocalDateTime grantDate2;

    @Column(name = "priz_stip")
    private Integer grantMark;

    @Column(name = "kurs_tek")
    private Integer currentCourse;

    @Column(name = "kurs_rector")
    private Integer rectorCourse;

}