package by.vstu.dean.models.specs;

import by.vstu.dean.core.models.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@ApiModel(description = "Объект специализации")
public class SpecializationModel extends DBBaseModel {

    /**
     * Название специализации.
     */
    @ApiModelProperty(notes = "Название специализации")
    @NotNull
    private String name;

    /**
     * Краткое название специализации.
     */
    @ApiModelProperty(notes = "Краткое название")
    @NotNull
    private String shortName;

    /**
     * Код специализации.
     */
    @ApiModelProperty(notes = "Код специальности")
    @NotNull
    private String spezCode;

    /**
     * Специальность.
     */
    @JoinColumn(name = "spec_id")
    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(notes = "Специальность")
    private SpecialityModel spec;

    /**
     * Квалификация.
     */
    @JoinColumn(name = "qualification_id")
    @ManyToOne
    @ApiModelProperty(notes = "Квалификация")
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
