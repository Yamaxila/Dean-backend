package by.vstu.dean.models.students;

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
 * Класс, представляющий объект учреждения образования.
 */
@Entity
@Table(name = "institutions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Модель учреждения образования")
public class InstitutionModel extends DBBaseModel {

    /**
     * Полное название учреждения образования.
     */
    @Schema(title = "Полное название")
    @NotNull
    private String fullName;

    /**
     * Краткое название учреждения образования.
     */
    @Schema(title = "Краткое название")
    private String shortName;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        InstitutionModel that = (InstitutionModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
