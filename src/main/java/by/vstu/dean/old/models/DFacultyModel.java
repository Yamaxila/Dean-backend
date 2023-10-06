package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Dfak", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dfak_id", nullable = false))
})
public class DFacultyModel extends OldDBBaseModel {
    @Column(name = "fak", nullable = false, length = 10)
    private String shortName;

    @Column(name = "fak_name", length = 50)
    private String name;

    @Column(name = "fak_po", length = 50)
    private String nameGenitive;

    @Column(name = "fak_d", length = 50)
    private String nameDative;

    @Column(name = "rektor", length = 30)
    private String rectorName;

    @Column(name = "dolg_dek", length = 50)
    private String dolgDek;

    @Column(name = "dekan", length = 30)
    private String dean;

    @Column(name = "sekretar", length = 30)
    private String clerkName;

    @Column(name = "prikaz_zach", length = 10)
    private String enrollMsgPaid;

    @Column(name = "data_zach")
    private LocalDateTime enrollDatePaid;

    @Column(name = "prikaz_zach_bud", length = 10)
    private String enrollMsgNotPaid;

    @Column(name = "data_zach_bud")
    private LocalDateTime enrollDateNotPaid;

    @Column(name = "priz_gur")
    private Integer journalType;

    @Column(name = "obuchenie", length = 8)
    private String facultyType;

    @Column(name = "god_uch1")
    private Integer startYear;

    @Column(name = "god_uch2")
    private Integer endYear;

    @Column(name = "semestr_uch", length = 10)
    private String semestr;

    @Column(name = "god_vip")
    private Integer releaseYear;

    @Column(name = "N_raspor", length = 10)
    private String moveMsgNumber;

    @Column(name = "data_raspor")
    private LocalDateTime moveMsgDate;

    @Column(name = "data_ses1")
    private LocalDateTime sessionDate1;

    @Column(name = "data_ses2")
    private LocalDateTime sessionDate2;

    @Column(name = "den", length = 3)
    private String day;

    @Column(name = "ch_zn", length = 1)
    private String chZn;

    @Column(name = "etapi")
    private Integer etapi;


    @Override
    public String toString() {
        return "DFacultyModel{" +
                "id='" + this.getId() + '\'' +
                "shortName='" + shortName + '\'' +
                '}';
    }
}