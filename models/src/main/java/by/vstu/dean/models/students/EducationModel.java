package by.vstu.dean.models.students;

import by.vstu.dean.core.models.DBBaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Класс, представляющий объект предыдущего места учебы.
 */
@Entity
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EducationModel extends DBBaseModel {

    /**
     * Студент, связанный с этим предыдущим местом учебы.
     */
    @JoinColumn(name = "student_id")
    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(notes = "Студент")
    @NotNull
    private StudentModel student;

    /**
     * Учреждение образования.
     */
    @ApiModelProperty(notes = "Учреждение образования")
    @NotNull
    private String education;

    /**
     * Тип документа об образовании.
     */
    @ApiModelProperty(notes = "Тип документа об образовании")
    @NotNull
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    @ApiModelProperty(notes = "Серия документа об образовании")
    @NotNull
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    @ApiModelProperty(notes = "Номер документа об образовании")
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
