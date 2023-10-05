package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.GroupModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "study_plan")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Объект учебных планов")
public class StudyPlanModel extends DBBaseModel {

    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Группа")
    private GroupModel group;
    @ApiModelProperty(notes = "Начало учебного года")
    private Integer yearStart;
    @ApiModelProperty(notes = "Конец учебного года")
    private Integer yearEnd;
    @ApiModelProperty(notes = "Номер семестра")
    private Integer semesterNumber;
    @ApiModelProperty(notes = "Семестр")
    private String semester;
    @ApiModelProperty(notes = "Курс группы")
    private Integer course;

    @NotNull
    @JoinColumn(name = "exam_type_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Тип зачетной единицы")
    private ExamModel exam;

    @NotNull
    @JoinColumn(name = "discipline_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Дисциплина")
    private DisciplineModel discipline;

    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Преподаватель")
    private TeacherModel teacher;

//    @Column(name = "nprep")
//    private Integer teacherId;

}
