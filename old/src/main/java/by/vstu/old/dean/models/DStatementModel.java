package by.vstu.old.dean.models;

import by.vstu.old.dean.OldDBBaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Dvedom", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dvedom_id", nullable = false))
})
public class DStatementModel extends OldDBBaseModel {

    @Column(name = "dfak_id")
    private Long facultyId;

    @Formula("(select Dgrup.kurs_tek from Dgrup where Dgrup.gr = gr)")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer groupCurrentCourse;

    @Formula("(select Dgrup.dgrup_id from Dgrup where Dgrup.gr = gr)")
    private Long groupId;

    @Column(name = "duchplan_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long studyPlanId;

    @Column(name = "data_ved")
    private LocalDateTime statementDate;

    @Column(name = "data_sdachi")
    private LocalDateTime passDate;

    @Column(name = "nved_dek")
    private Integer facultyStatementNumber;

    @Column(name = "nved")
    private Integer globalStatementNumber;

    @Column(name = "Nekz_lista")
    private Integer examSheetNumber;

    @Column(name = "dstudent_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long studentId;

    @JoinColumn(name = "docenka_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DGradeModel grade;

    @Column(name = "ekz_list1")
    private Integer retakeExamSheetNumber1;

    @Column(name = "Nprep1")
    private Long retakeTeacherId1;

    @Column(name = "Nprep1", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Long retakeTeacher1Id;

    @Column(name = "data_per1")
    private LocalDateTime retakeDate1;

    @Column(name = "ekz_list2")
    private Integer retakeExamSheetNumber2;

    @Column(name = "Nprep2")
    private Long retakeTeacherId2;

    @Column(name = "Nprep2", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Long retakeTeacher2Id;

    @Column(name = "data_per2")
    private LocalDateTime retakeDate2;

    @Column(name = "ekz_list3")
    private Integer retakeExamSheetNumber3;

    @Column(name = "Nprep3")
    private Long retakeTeacherId3;

    @Column(name = "Nprep3", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Long retakeTeacher3Id;

    @Column(name = "data_per3")
    private LocalDateTime retakeDate3;

    @Column(name = "N_semestra")
    private Integer semesterNumber;

    @Column(name = "kurs")
    private Integer course;

    @Column(name = "ndis")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long disciplineId;
}