package by.vstu.dean.models;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс, представляющий объект факультета.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dean_faculties")
@Schema(title = "Модель факультета")
public class FacultyModel extends DBBaseModel {

    /**
     * Краткое название факультета.
     */
    @Schema(title = "Краткое название")
    @NotNull
    private String shortName;

    /**
     * Полное название факультета.
     */
    @Schema(title = "Полное название")
    @NotNull
    private String name;

    /**
     * Название факультета в родительном падеже.
     */
    @Schema(title = "Название в родительном падеже")
    private String nameGenitive;

    /**
     * Название факультета в дательном падеже.
     */
    @Schema(title = "Название в дательном падеже")
    private String nameDative;

    /**
     * Ректор факультета.
     */
    @Schema(title = "Ректор")
    private String rectorName;

    /**
     * Декан факультета.
     */
    @Schema(title = "Декан")
    private String dean;

    /**
     * Методист/Секретарь факультета.
     */
    @Schema(title = "Методист/Секретарь")
    private String clerkName;

    /**
     * Приказ о зачислении платников.
     */
    @Schema(title = "Приказ о зачислении платников")
    private String enrollMsgPaid;

    @Schema(title = "Дата зачисления бесплатников")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate enrollDatePaid;

    /**
     * Приказ о зачислении бесплатников.
     */
    @Schema(title = "Приказ о зачислении бесплатников")
    private String enrollMsgNotPaid;

    /**
     * Дата зачисления бесплатников.
     */
    @Schema(title = "Дата зачисления бесплатников")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate enrollDateNotPaid;

    /**
     * Тип журнала (ведется на год, семестр)... со слов Зуевой.
     */
    @Schema(title = "Тип журнала (ведется на год, семестр)... со слов Зуевой...")
    private Integer journalType;

    /**
     * Тип факультета.
     */
    @Schema(title = "Тип факультета")
    private Integer facultyType;

    /**
     * Год начала семестра.
     */
    @Schema(title = "Год начала семестра")
    private Integer semesterStartYear;

    /**
     * Год конца семестра.
     */
    @Schema(title = "Год конца семестра")
    private Integer semesterEndYear;

    /**
     * Семестр.
     */
    @Schema(title = "Семестр")
    private String semester;

    /**
     * Приказ о переводе.
     */
    @Schema(title = "Приказ о переводе")
    private String moveMsgNumber;

    /**
     * Дата приказа о переводе.
     */
    @Schema(title = "Дата приказа о переводе")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate moveMsgDate;

    /**
     * Тип образования (устаревшее поле).
     */
    @Schema(title = "Тип образования")
    @Deprecated
    private Integer educationType;

    public boolean isDaytime() {
        return this.getFacultyType() == 0 && this.getSourceId() != 8;
    }

    @Override
    public String toString() {
        return "FacultyModel{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        FacultyModel that = (FacultyModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
