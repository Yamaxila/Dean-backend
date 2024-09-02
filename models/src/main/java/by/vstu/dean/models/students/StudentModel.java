package by.vstu.dean.models.students;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.specs.SpecializationModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс, представляющий объект студента.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Schema(description = "Объект студента")
public class StudentModel extends DBBaseModel {

    /**
     * Фамилия студента.
     */
    @NotNull
    @Schema(title = "Фамилия")
    private String lastName;

    /**
     * Имя студента.
     */
    @NotNull
    @Schema(title = "Имя")
    private String firstName;

    /**
     * Отчество студента.
     */
    @NotNull
    @Schema(title = "Отчество")
    private String secondName;

    /**
     * Пол студента.
     */
    @Schema(title = "Пол")
    private Integer sex;

    /**
     * Тип города студента.
     */
    @Schema(title = "Тип города")
    private Integer cityType;

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Schema(title = "Адрес")
    @Deprecated
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    @Schema(title = "Страна")
    private String addressCountry;

    /**
     * Почтовый индекс адреса студента.
     */
    @Schema(title = "Почтовый индекс")
    private String addressIndex;

    /**
     * Область адреса студента.
     */
    @Schema(title = "Область")
    private String addressState;

    /**
     * Район адреса студента.
     */
    @Schema(title = "Район")
    private String addressRegion;

    /**
     * Город адреса студента.
     */
    @Schema(title = "Город")
    private String addressCity;

    /**
     * Улица адреса студента.
     */
    @Schema(title = "Улица")
    private String addressStreet;

    /**
     * Дом адреса студента.
     */
    @Schema(title = "Дом")
    private String addressHouse;

    /**
     * Корпус адреса студента.
     */
    @Schema(title = "Корпус")
    private String addressHousePart;

    /**
     * Квартира адреса студента.
     */
    @Schema(title = "Квартира")
    private String addressFlat;

    /**
     * Телефон студента.
     */
    @Schema(title = "Телефон")
    private String phone;

    /**
     * Льготы студента.
     */
    @Schema(title = "Льготы")
    private String benefits;


    /**
     * Является ли город студента деревней.
     */
    @Schema(title = "Является ли город - деревней")
    private boolean cityIsVillage;

    /**
     * Последний документ студента.
     */
    @JoinColumn(name = "last_document_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    @Schema(title = "Последний документ")
    @NotNull
    private DocumentModel lastDocument;

    /**
     * Последнее отклонение студента.
     */
    @SuppressWarnings({"deprecation"})
    @JoinColumn(name = "last_deviation_id")
    @ManyToOne
    @Schema(title = "Последнее отклонение")
    private DeviationModel lastDeviation;

    /**
     * Специализация студента.
     */
    @JoinColumn(name = "spez_id")
    @ManyToOne
    @Schema(title = "Специализация")
    private SpecializationModel specialization;

    /**
     * Группа, к которой принадлежит студент.
     */
    @JoinColumn(name = "group_id")
    @ManyToOne
    @NotNull
    @Schema(title = "Группа")
    private GroupModel group;

    /**
     * Комната, в которой проживает студент.
     */
    @JoinColumn(name = "hostel_room_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @Schema(title = "Комната")
    @JsonBackReference
    private HostelRoomModel hostelRoom;

    /**
     * Дата заселения студента.
     */
    @Schema(title = "Дата заселения")
    private LocalDate checkInDate;

    /**
     * Дата выселения студента.
     */
    @Schema(title = "Дата выселения")
    private LocalDate evictionDate;

    @Column(name = "approved", columnDefinition = "boolean default false")
    @NotNull
    private boolean approved;

    /**
     * URL-адрес фото студента.
     */
    @Schema(title = "URL-адрес фото студента")
    private String photoUrl;

    @Override
    public String toString() {
        return "StudentModel{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", sex=" + sex +
                ", cityType=" + cityType +
                ", address='" + address + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                ", addressIndex='" + addressIndex + '\'' +
                ", addressState='" + addressState + '\'' +
                ", addressRegion='" + addressRegion + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressHouse='" + addressHouse + '\'' +
                ", addressHousePart='" + addressHousePart + '\'' +
                ", addressFlat='" + addressFlat + '\'' +
                ", phone='" + phone + '\'' +
                ", benefits='" + benefits + '\'' +
                ", cityIsVillage=" + cityIsVillage +
                ", lastDeviation=" + lastDeviation +
                ", specialization=" + specialization +
                ", group=" + group +
                ", hostelRoom=" + hostelRoom +
                ", checkInDate=" + checkInDate +
                ", evictionDate=" + evictionDate +
                ", isApproved=" + approved +
                ", photoUrl=" + photoUrl +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StudentModel that = (StudentModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
