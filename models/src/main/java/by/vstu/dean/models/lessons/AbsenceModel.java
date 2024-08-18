package by.vstu.dean.models.lessons;

import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.students.StudentModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "absences")
@Setter
@Getter
public class AbsenceModel extends DBBaseModel {

    private LocalDate date;
    @JoinColumn(name = "discipline_id")
    @ManyToOne
    @NotNull
    private DisciplineModel discipline;

    @JoinColumn(name = "department_id")
    @ManyToOne
    @NotNull
    private DepartmentModel department;

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @NotNull
    private TeacherModel teacherModel;
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private ELessonType lessonType;
    @NotNull
    private Integer lessonNumber;
    @JoinColumn(name = "student_id")
    @ManyToOne
    @NotNull
    private StudentModel student;
    @NotNull
    private Double absenceTime;
    @NotNull
    private Double hourPrice;
    private LocalDate paymentDate;
    private LocalDate dateCompleted;
    private LocalDate datePrint;
    @Column(length = 100)
    private String reasonMsg;
    private LocalDate dateErip;
    private boolean printed;
}
