package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель объекта дисциплины.
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
@Schema(title = "Модель дисциплины")
public class DisciplineModel extends DBBaseModel {

    /**
     * Название дисциплины.
     */
    @Schema(title = "Название дисциплины")
    @NotNull
    private String name;

    /**
     * Краткое название дисциплины.
     */
    @Schema(title = "Краткое название дисциплины")
    @NotNull
    private String shortName;

    /**
     * Кафедра, к которой относится дисциплина.
     */
    @JoinColumn(name = "department_id")
    @ManyToOne
    @Schema(title = "Кафедра")
    private DepartmentModel department;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DisciplineModel that = (DisciplineModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
