package by.vstu.dean.future.models.merge;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Модель объекта связи преподавателя и кафедры.
 */
@Entity
@Table(name = "teacher_department_merge")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ApiModel(description = "Связующий объект преподаватель-кафедра")
public class TeacherDepartmentMerge extends DBBaseModel {

    /**
     * Преподаватель.
     */
    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @ApiModelProperty(notes = "Преподаватель")
    private TeacherModel teacher;

    /**
     * Кафедра.
     */
    @JoinColumn(name = "department_id")
    @ManyToOne
    @ApiModelProperty(notes = "Кафедра")
    private DepartmentModel department;
}
