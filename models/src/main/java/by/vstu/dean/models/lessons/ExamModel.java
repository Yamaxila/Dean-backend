package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ExamType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель объекта зачетной единицы.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dean_exams")
@Schema(title = "Модель зачетной единицы")
public class ExamModel extends DBBaseModel {

    /**
     * Название зачетной единицы.
     */
    @Schema(title = "Название зачетной единицы")
    @NotNull
    private String name;

    /**
     * Тип экзамена.
     */
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private ExamType type;

    public String toString() {
        return "ExamModel(name=" + this.getName() + ", type=" + this.getType() + ")";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ExamModel examModel = (ExamModel) o;
        return getId() != null && Objects.equals(getId(), examModel.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
