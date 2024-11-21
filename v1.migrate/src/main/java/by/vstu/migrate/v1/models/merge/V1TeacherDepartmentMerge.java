package by.vstu.migrate.v1.models.merge;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.models.lessons.V1DepartmentModel;
import by.vstu.migrate.v1.models.lessons.V1TeacherModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель объекта связи преподавателя и кафедры.
 */
@Entity
@Table(name = "teacher_department_merge")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class V1TeacherDepartmentMerge extends V1DBBaseModel {

    /**
     * Преподаватель.
     */
    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @JsonBackReference
    private V1TeacherModel teacher;

    /**
     * Кафедра.
     */
    @JoinColumn(name = "department_id")
    @ManyToOne
    @JsonBackReference
    private V1DepartmentModel department;
}
