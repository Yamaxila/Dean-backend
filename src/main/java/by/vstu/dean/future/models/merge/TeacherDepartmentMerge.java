package by.vstu.dean.future.models.merge;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "teacher_department_merge")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Связующий объект преподаватель-кафедра")
public class TeacherDepartmentMerge extends DBBaseModel {

    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @ApiModelProperty(notes = "Преподаватель")
    private TeacherModel teacher;

    @JoinColumn(name = "department_id")
    @ManyToOne
    @ApiModelProperty(notes = "Кафедра")
    private DepartmentModel department;

}
