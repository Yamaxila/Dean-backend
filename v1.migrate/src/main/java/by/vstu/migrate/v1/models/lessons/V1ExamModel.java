package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.enums.V1ExamType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Модель объекта зачетной единицы.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams")
public class V1ExamModel extends V1DBBaseModel {

    /**
     * Название зачетной единицы.
     */
    @NotNull
    private String name;

    /**
     * Тип экзамена.
     */
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private V1ExamType type;

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull V1ExamType getType() {
        return this.type;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setType(@NotNull V1ExamType type) {
        this.type = type;
    }

    public String toString() {
        return "ExamModel(name=" + this.getName() + ", type=" + this.getType() + ")";
    }
}
