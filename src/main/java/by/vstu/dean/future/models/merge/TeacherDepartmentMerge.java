package by.vstu.dean.future.models.merge;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "teacher_department_merge")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDepartmentMerge extends DBBaseModel {

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    private TeacherModel teacher;

    @JoinColumn(name = "department_id")
    @ManyToOne
    private DepartmentModel department;

}
