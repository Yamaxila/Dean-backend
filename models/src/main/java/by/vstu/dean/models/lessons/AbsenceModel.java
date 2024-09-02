package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.students.StudentModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AbsenceModel that = (AbsenceModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
