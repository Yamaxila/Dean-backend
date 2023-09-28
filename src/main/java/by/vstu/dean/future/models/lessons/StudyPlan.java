package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.GroupModel;
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
public class StudyPlan extends DBBaseModel {

    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne
    private GroupModel group;

    private Integer yearStart;
    private Integer yearEnd;

    private Integer semesterNumber;
    private String semester;

    private Integer course;

    @NotNull
    @JoinColumn(name = "exam_type_id", nullable = false)
    @ManyToOne
    private ExamModel exam;

    @NotNull
    @JoinColumn(name = "discipline_id", nullable = false)
    @ManyToOne
    private DisciplineModel discipline;

    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false)
    @ManyToOne
    private TeacherModel teacher;

//    @Column(name = "nprep")
//    private Integer teacherId;

}
