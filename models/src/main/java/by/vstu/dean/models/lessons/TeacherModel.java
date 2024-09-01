package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
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
 * Модель объекта преподавателя.
 */
@Entity
@Setter
@Getter
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект преподавателя")
public class TeacherModel extends DBBaseModel {

    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия преподавателя")
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя преподавателя")
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество преподавателя")
    private String patronymic;

    /**
     * Звание преподавателя.
     */
    @JoinColumn(name = "degree_id")
    @ManyToOne
    @ApiModelProperty(notes = "Звание преподавателя")
    private TeacherDegreeModel degree;

    public String toString() {
        return "TeacherModel(surname=" + this.getSurname() + ", name=" + this.getName() + ", patronymic=" + this.getPatronymic() + ", degree=" + this.getDegree() + ")";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TeacherModel that = (TeacherModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
