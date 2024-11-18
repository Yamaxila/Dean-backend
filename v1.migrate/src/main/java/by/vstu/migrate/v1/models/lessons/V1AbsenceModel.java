package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.enums.V1ELessonType;
import by.vstu.migrate.v1.models.students.V1StudentModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "absences")
@Setter
@Getter
public class V1AbsenceModel extends V1DBBaseModel {

    private LocalDate date;
    @JoinColumn(name = "discipline_id")
    @ManyToOne
    @NotNull
    private V1DisciplineModel discipline;

    @JoinColumn(name = "department_id")
    @ManyToOne
    @NotNull
    private V1DepartmentModel department;

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @NotNull
    private V1TeacherModel teacherModel;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private V1ELessonType lessonType;

    @NotNull
    private Integer lessonNumber;

    @JoinColumn(name = "student_id")
    @ManyToOne
    @NotNull
    private V1StudentModel student;

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
