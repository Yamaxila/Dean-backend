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

    public String toString() {
        return "ExamModel(name=" + this.getName() + ", type=" + this.getType() + ")";
    }
}
