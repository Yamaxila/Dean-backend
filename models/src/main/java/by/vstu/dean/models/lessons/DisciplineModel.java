package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@ApiModel(description = "Объект дисциплины")
public class DisciplineModel extends DBBaseModel {

    /**
     * Название дисциплины.
     */
    @ApiModelProperty(notes = "Название дисциплины")
    @NotNull
    private String name;

    /**
     * Краткое название дисциплины.
     */
    @ApiModelProperty(notes = "Краткое название дисциплины")
    @NotNull
    private String shortName;

    /**
     * Кафедра, к которой относится дисциплина.
     */
    @JoinColumn(name = "department_id")
    @ManyToOne
    @ApiModelProperty(notes = "Кафедра")
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
