package by.vstu.dean.models.specs;

import by.vstu.dean.core.models.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель объекта специализации.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specializations")
@Schema(description = "Объект специализации")
public class SpecializationModel extends DBBaseModel {

    /**
     * Название специализации.
     */
    @Schema(title = "Название специализации")
    @NotNull
    private String name;

    /**
     * Краткое название специализации.
     */
    @Schema(title = "Краткое название")
    @NotNull
    private String shortName;

    /**
     * Код специализации.
     */
    @Schema(title = "Код специальности")
    @NotNull
    private String spezCode;

    /**
     * Специальность.
     */
    @JoinColumn(name = "spec_id")
    @ManyToOne
    @JsonIgnore
    @Schema(title = "Специальность")
    private SpecialityModel spec;

    /**
     * Квалификация.
     */
    @JoinColumn(name = "qualification_id")
    @ManyToOne
    @Schema(title = "Квалификация")
    @NotNull
    private QualificationModel qualification;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SpecializationModel that = (SpecializationModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
