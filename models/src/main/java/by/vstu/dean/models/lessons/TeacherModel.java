package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель объекта преподавателя.
 */
@Entity
@Setter
@Getter
@Table(name = "dean_teachers")
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Модель преподавателя")
public class TeacherModel extends DBBaseModel {

    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @Schema(title = "Фамилия преподавателя")
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @Schema(title = "Имя преподавателя")
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @Schema(title = "Отчество преподавателя")
    private String patronymic;

    /**
     * Звание преподавателя.
     */
    @JoinColumn(name = "degree_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Schema(title = "Звание преподавателя")
    private TeacherDegreeModel degree;

    @Schema(title = "Фото преподавателя")
    private String photoUrl;

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

    public String getFullName() {
        return "%s %s. %s.".formatted(this.surname, this.name.charAt(0), this.patronymic.charAt(0));
    }
}
