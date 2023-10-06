package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Модель объекта должности преподавателя.
 */
@Entity
@Table(name = "teacher_degrees")
@ApiModel(description = "Объект должности преподавателя")
public class TeacherDegreeModel extends DBBaseModel {

    /**
     * Название должности.
     */
    @ApiModelProperty(notes = "Название должности")
    private String name;

    public TeacherDegreeModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TeacherDegreeModel)) return false;
        final TeacherDegreeModel other = (TeacherDegreeModel) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TeacherDegreeModel;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "TeacherDegreeModel(name=" + this.getName() + ")";
    }
}
