package by.vstu.dean.models.specs;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.lessons.DepartmentModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Модель объекта специальности.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialities")
@ApiModel(description = "Объект специальности")
public class SpecialityModel extends DBBaseModel {

    /**
     * Название специальности.
     */
    @ApiModelProperty(notes = "Название специальности")
    @NotNull
    private String name;

    /**
     * Краткое название специальности.
     */
    @ApiModelProperty(notes = "Краткое название")
    @NotNull
    private String shortName;

    /**
     * Код специальности.
     */
    @ApiModelProperty(notes = "Код специальности")
    @NotNull
    private String specCode;

    /**
     * Кафедра, привязанная к специальности.
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @ApiModelProperty(notes = "Кафедра, привязанная к специальности")
    private DepartmentModel department;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SpecialityModel that = (SpecialityModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
