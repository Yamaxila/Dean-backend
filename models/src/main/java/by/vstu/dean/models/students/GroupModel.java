package by.vstu.dean.models.students;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.ESemester;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.models.specs.SpecialityModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс, представляющий объект группы.
 */
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
@Schema(title = "Модель группы")
public class GroupModel extends DBBaseModel {

    /**
     * Название группы.
     */
    @Schema(title = "Группа")
    @NotNull
    private String name;

    /**
     * Специальность, связанная с данной группой.
     */
    @ManyToOne
    @JoinColumn(name = "spec_id")
    @Schema(title = "Специальность")
    @NotNull
    private SpecialityModel spec;

    /**
     * Факультет, связанный с данной группой.
     */
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @Schema(title = "Факультет")
    @NotNull
    private FacultyModel faculty;

    /**
     * Год поступления группы.
     */
    @Schema(title = "Год поступления")
    private Integer yearStart;

    /**
     * Семестр окончания
     */
    @Enumerated(EnumType.ORDINAL)
    private ESemester endSemester;

    /**
     * Год окончания обучения группы.
     */
    @Schema(title = "Год окончания")
    private Integer yearEnd;

    /**
     * Дата начала обучения группы.
     */
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата поступления???") //FIXME
    private LocalDate dateStart;

    /**
     * Дата окончания обучения группы.
     */
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата окончания")
    private LocalDate dateEnd;

    /**
     * Средний балл студентов в группе.
     */
    @Schema(title = "Средний балл")
    private Double score;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        GroupModel that = (GroupModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
