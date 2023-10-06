package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.GroupModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Модель объекта учебного плана.
 */
@Entity
@Table(name = "study_plan")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект учебных планов")
public class StudyPlanModel extends DBBaseModel {

    /**
     * Группа.
     */
    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Группа")
    private GroupModel group;

    /**
     * Начало учебного года.
     */
    @ApiModelProperty(notes = "Начало учебного года")
    private Integer yearStart;

    /**
     * Конец учебного года.
     */
    @ApiModelProperty(notes = "Конец учебного года")
    private Integer yearEnd;

    /**
     * Номер семестра.
     */
    @ApiModelProperty(notes = "Номер семестра")
    private Integer semesterNumber;

    /**
     * Семестр.
     */
    @ApiModelProperty(notes = "Семестр")
    private String semester;

    /**
     * Курс группы.
     */
    @ApiModelProperty(notes = "Курс группы")
    private Integer course;

    /**
     * Тип зачетной единицы.
     */
    @NotNull
    @JoinColumn(name = "exam_type_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Тип зачетной единицы")
    private ExamModel exam;

    /**
     * Дисциплина.
     */
    @NotNull
    @JoinColumn(name = "discipline_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Дисциплина")
    private DisciplineModel discipline;

    /**
     * Преподаватель.
     */
    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Преподаватель")
    private TeacherModel teacher;

    public @NotNull GroupModel getGroup() {
        return this.group;
    }

    public Integer getYearStart() {
        return this.yearStart;
    }

    public Integer getYearEnd() {
        return this.yearEnd;
    }

    public Integer getSemesterNumber() {
        return this.semesterNumber;
    }

    public String getSemester() {
        return this.semester;
    }

    public Integer getCourse() {
        return this.course;
    }

    public @NotNull ExamModel getExam() {
        return this.exam;
    }

    public @NotNull DisciplineModel getDiscipline() {
        return this.discipline;
    }

    public @NotNull TeacherModel getTeacher() {
        return this.teacher;
    }

    public void setGroup(@NotNull GroupModel group) {
        this.group = group;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public void setExam(@NotNull ExamModel exam) {
        this.exam = exam;
    }

    public void setDiscipline(@NotNull DisciplineModel discipline) {
        this.discipline = discipline;
    }

    public void setTeacher(@NotNull TeacherModel teacher) {
        this.teacher = teacher;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StudyPlanModel)) return false;
        final StudyPlanModel other = (StudyPlanModel) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$group = this.getGroup();
        final Object other$group = other.getGroup();
        if (this$group == null ? other$group != null : !this$group.equals(other$group)) return false;
        final Object this$yearStart = this.getYearStart();
        final Object other$yearStart = other.getYearStart();
        if (this$yearStart == null ? other$yearStart != null : !this$yearStart.equals(other$yearStart)) return false;
        final Object this$yearEnd = this.getYearEnd();
        final Object other$yearEnd = other.getYearEnd();
        if (this$yearEnd == null ? other$yearEnd != null : !this$yearEnd.equals(other$yearEnd)) return false;
        final Object this$semesterNumber = this.getSemesterNumber();
        final Object other$semesterNumber = other.getSemesterNumber();
        if (this$semesterNumber == null ? other$semesterNumber != null : !this$semesterNumber.equals(other$semesterNumber))
            return false;
        final Object this$semester = this.getSemester();
        final Object other$semester = other.getSemester();
        if (this$semester == null ? other$semester != null : !this$semester.equals(other$semester)) return false;
        final Object this$course = this.getCourse();
        final Object other$course = other.getCourse();
        if (this$course == null ? other$course != null : !this$course.equals(other$course)) return false;
        final Object this$exam = this.getExam();
        final Object other$exam = other.getExam();
        if (this$exam == null ? other$exam != null : !this$exam.equals(other$exam)) return false;
        final Object this$discipline = this.getDiscipline();
        final Object other$discipline = other.getDiscipline();
        if (this$discipline == null ? other$discipline != null : !this$discipline.equals(other$discipline))
            return false;
        final Object this$teacher = this.getTeacher();
        final Object other$teacher = other.getTeacher();
        if (this$teacher == null ? other$teacher != null : !this$teacher.equals(other$teacher)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StudyPlanModel;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $group = this.getGroup();
        result = result * PRIME + ($group == null ? 43 : $group.hashCode());
        final Object $yearStart = this.getYearStart();
        result = result * PRIME + ($yearStart == null ? 43 : $yearStart.hashCode());
        final Object $yearEnd = this.getYearEnd();
        result = result * PRIME + ($yearEnd == null ? 43 : $yearEnd.hashCode());
        final Object $semesterNumber = this.getSemesterNumber();
        result = result * PRIME + ($semesterNumber == null ? 43 : $semesterNumber.hashCode());
        final Object $semester = this.getSemester();
        result = result * PRIME + ($semester == null ? 43 : $semester.hashCode());
        final Object $course = this.getCourse();
        result = result * PRIME + ($course == null ? 43 : $course.hashCode());
        final Object $exam = this.getExam();
        result = result * PRIME + ($exam == null ? 43 : $exam.hashCode());
        final Object $discipline = this.getDiscipline();
        result = result * PRIME + ($discipline == null ? 43 : $discipline.hashCode());
        final Object $teacher = this.getTeacher();
        result = result * PRIME + ($teacher == null ? 43 : $teacher.hashCode());
        return result;
    }

    public String toString() {
        return "StudyPlanModel(group=" + this.getGroup() + ", yearStart=" + this.getYearStart() + ", yearEnd=" + this.getYearEnd() + ", semesterNumber=" + this.getSemesterNumber() + ", semester=" + this.getSemester() + ", course=" + this.getCourse() + ", exam=" + this.getExam() + ", discipline=" + this.getDiscipline() + ", teacher=" + this.getTeacher() + ")";
    }
}
