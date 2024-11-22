package by.vstu.dean.models.merge;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.lessons.TeacherModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dean_statement_teacher_merge")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(title = "Модель связи оценка-преподаватель")
public class StatementTeacherMerge extends DBBaseModel {

    @JoinColumn(name = "ssm_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private StatementStudentMerge ssm;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TeacherModel teacher;

}
