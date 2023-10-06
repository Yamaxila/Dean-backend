package by.vstu.dean.future.models.lessons;

import by.vstu.dean.enums.ExamType;
import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Модель объекта зачетной единицы.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams")
@ApiModel(description = "Объект зачетной единицы")
public class ExamModel extends DBBaseModel {

    /**
     * Название зачетной единицы.
     */
    @ApiModelProperty(notes = "Название зачетной единицы")
    @NotNull
    private String name;

    /**
     * Тип экзамена.
     */
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private ExamType type;

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull ExamType getType() {
        return this.type;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setType(@NotNull ExamType type) {
        this.type = type;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ExamModel)) return false;
        final ExamModel other = (ExamModel) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ExamModel;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    public String toString() {
        return "ExamModel(name=" + this.getName() + ", type=" + this.getType() + ")";
    }
}
