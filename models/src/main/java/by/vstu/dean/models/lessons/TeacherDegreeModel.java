package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель объекта должности преподавателя.
 */
@Getter
@Entity
@Table(name = "teacher_degrees")
@Schema(description = "Объект должности преподавателя")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDegreeModel extends DBBaseModel {

    /**
     * Название должности.
     */
    @Schema(title = "Название должности")
    @NotNull
    private String name;

    /**
     * Оплата за 1 час
     */
    @Schema(title = "Оплата за 1 час")
    private Double hourPrice;

    public String toString() {
        return "TeacherDegreeModel(name=" + this.getName() + ")";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TeacherDegreeModel that = (TeacherDegreeModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
