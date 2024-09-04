package by.vstu.dean.models.students;

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
 * Класс, представляющий объект предыдущего места учебы.
 */
@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(title = "Модель образования студента")
public class EducationModel extends DBBaseModel {

    /**
     * Студент, связанный с этим предыдущим местом учебы.
     */
    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    @Schema(title = "Студент")
    @NotNull
    private StudentModel student;

    /**
     * Учреждение образования.
     */
    @Schema(title = "Учреждение образования")
    @NotNull
    private String education;

    /**
     * Тип документа об образовании.
     */
    @Schema(title = "Тип документа об образовании")
    @NotNull
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    @Schema(title = "Серия документа об образовании")
    @NotNull
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    @Schema(title = "Номер документа об образовании")
    @NotNull
    private String educationDocumentNumber;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EducationModel that = (EducationModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
