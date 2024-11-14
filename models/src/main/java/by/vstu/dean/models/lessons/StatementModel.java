package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ESemester;
import by.vstu.dean.models.merge.StatementStudentMerge;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Модель объекта ведомости
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dean_statements")
@Schema(title = "Объект ведомости")
public class StatementModel extends DBBaseModel {

    /**
     * План, к которому относится ведомость
     */
    @Schema(title = "План, к которому относится ведомость")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_plan_id", nullable = false)
    private StudyPlanModel studyPlan;

    /**
     * Номер ведомости для группы
     */
    @Schema(title = "Номер ведомости для группы")
    private Integer groupStatementNumber;

    /**
     * Дата ведомости
     */
    @Schema(title = "Дата ведомости")
    private LocalDate statementDate;

    /**
     * Номер семестра, в котором проводилась оценка
     */
    @Schema(title = "Cеместр, в котором проводилась оценка")
    @Enumerated(EnumType.ORDINAL)
    private ESemester semesterType;

    /**
     * Курс, на котором проводилась оценка
     */
    @Schema(title = "Курс, на котором проводилась оценка")
    private Integer course;

    /**
     * Все оценки студентов, что сдавали по данной ведомости
     */
    //Возможно, будет очень страшно потом
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dean_statement_student_merge", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "statement_id")})
    @Schema(title = "Все оценки студентов, что сдавали по данной ведомости")
    private List<StatementStudentMerge> statementStudents;

    /**
     * Высчитывает номер семестра в зависимости от курса сдачи и семестра
     *
     * @return номер семестра
     */
    public int calculateSemesterNumber() {
        //1 курс * 2 = 2
        //2 - 1 = 1
        //Если семестр - ВЕСНА, то будет +1
        //1 + 1 - 2 семестр

        //3 курс * 2 = 6
        //6 - 1 = 5 (осенний семестр 3-го курса)
        //Если семестр - ВЕСНА, то будет +1
        //5 + 1 = 6 (весенний семестр третьего курса)

        //P.S. если семестр неизвестен (например, курсы какие-то тут будут), то мы так и считаем, что семестра нет
        return (this.course * 2) - 1 + (this.semesterType == ESemester.SPRING ? 1 : 0);
    }

}
