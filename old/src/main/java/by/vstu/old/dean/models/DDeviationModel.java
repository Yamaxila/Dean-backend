package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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

    @JoinColumn(name = "delo")
    @OneToMany // Тут может быть несколько записей.
    private List<DStudentModel> students;

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


    //Такой вот костыль из-за того, что номер дела не является уникальным
    public DStudentModel findLastStudent() {
        if (this.students == null || this.students.isEmpty())
            return null;
        if (this.students.size() == 1)
            return this.students.get(0);

        return this.students.stream().min(Comparator.comparing(DStudentModel::getEnrollmentDate)).orElse(null);
    }

}