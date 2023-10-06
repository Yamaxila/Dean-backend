package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
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
 * Модель объекта преподавателя.
 */
@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект преподавателя")
public class TeacherModel extends DBBaseModel {

    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия преподавателя")
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя преподавателя")
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество преподавателя")
    private String patronymic;

    /**
     * Должность преподавателя.
     */
    @JoinColumn(name = "degree_id")
    @ManyToOne
    @ApiModelProperty(notes = "Должность преподавателя")
    private TeacherDegreeModel degree;

    public @NotNull String getSurname() {
        return this.surname;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull String getPatronymic() {
        return this.patronymic;
    }

    public TeacherDegreeModel getDegree() {
        return this.degree;
    }

    public void setSurname(@NotNull String surname) {
        this.surname = surname;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setPatronymic(@NotNull String patronymic) {
        this.patronymic = patronymic;
    }

    public void setDegree(TeacherDegreeModel degree) {
        this.degree = degree;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TeacherModel)) return false;
        final TeacherModel other = (TeacherModel) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$surname = this.getSurname();
        final Object other$surname = other.getSurname();
        if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$patronymic = this.getPatronymic();
        final Object other$patronymic = other.getPatronymic();
        if (this$patronymic == null ? other$patronymic != null : !this$patronymic.equals(other$patronymic))
            return false;
        final Object this$degree = this.getDegree();
        final Object other$degree = other.getDegree();
        if (this$degree == null ? other$degree != null : !this$degree.equals(other$degree)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TeacherModel;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $surname = this.getSurname();
        result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $patronymic = this.getPatronymic();
        result = result * PRIME + ($patronymic == null ? 43 : $patronymic.hashCode());
        final Object $degree = this.getDegree();
        result = result * PRIME + ($degree == null ? 43 : $degree.hashCode());
        return result;
    }

    public String toString() {
        return "TeacherModel(surname=" + this.getSurname() + ", name=" + this.getName() + ", patronymic=" + this.getPatronymic() + ", degree=" + this.getDegree() + ")";
    }
}
