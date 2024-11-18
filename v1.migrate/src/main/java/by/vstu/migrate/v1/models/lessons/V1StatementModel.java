package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.models.V1FacultyModel;
import by.vstu.migrate.v1.models.students.V1StudentModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Модель объекта ведомости
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statements")
public class V1StatementModel extends V1DBBaseModel {
    /**
     * Факультет, к которому относится ведомость
     */
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private V1FacultyModel faculty;

    /**
     * План, к которому относится ведомость
     */
    @ManyToOne
    @JoinColumn(name = "study_plan_id", nullable = false)
    private V1StudyPlanModel studyPlan;

    /**
     * Дата ведомости
     */
    private LocalDateTime statementDate;

    /**
     * Дата сдачи зачётной единицы
     */
    @Column(name = "last_pass_date")
    private LocalDateTime passDate;

    /**
     * Номер экзаменационного листа
     */
    @Column(name = "last_exam_sheet")
    private Integer examSheetNumber;

    /**
     * Студент, сдававший зачётную единицу
     */
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private V1StudentModel student;

    /**
     * Оценка, которую получил студент
     */
    @ManyToOne
    @JoinColumn(name = "grade_id")
    private V1GradeModel grade;

    /**
     * Преподаватель, который осуществлял оценку
     */
    @ManyToOne
    @JoinColumn(name = "last_teacher_id")
    private V1TeacherModel teacher;

    /**
     * Номер семестра, в котором проводилась оценка
     */
    private Integer semesterNumber;

    /**
     * Курс, на котором проводилась оценка
     */
    private Integer course;

    /**
     * Дисциплина, по которой проводилась оценка
     */
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private V1DisciplineModel discipline;

    /**
     * Тип зачётной единицы
     */
    @JoinColumn(name = "exam_type_id")
    @ManyToOne
    private V1ExamModel exam;

    /**
     * Была ли пересдача
     */
    @Column(name = "retake", columnDefinition = "boolean default false")
    private Boolean isRetake;
}
