package by.vstu.migrate.v1.models.students;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.models.hostels.V1HostelRoomModel;
import by.vstu.migrate.v1.models.specs.V1SpecializationModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;

/**
 * Класс, представляющий объект студента.
 */
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class V1StudentModel extends V1DBBaseModel {

    /**
     * Фамилия студента.
     */
    @NotNull
    private String lastName;

    /**
     * Имя студента.
     */
    @NotNull
    private String firstName;

    /**
     * Отчество студента.
     */
    @NotNull
    private String secondName;

    /**
     * Пол студента.
     */
    private Integer sex;

    /**
     * Дата рождения студента.
     */
    private LocalDate birthDate;

    /**
     * Иностранец ли студент.
     */
    @Column(name = "foreigner", columnDefinition = "boolean default false")
    @NotNull
    private boolean isForeigner = false;

    /**
     * ФИО матери студента.
     */
    private String motherFullName;

    /**
     * Телефон матери студента.
     */
    private String motherPhone;

    /**
     * ФИО отца студента.
     */
    private String fatherFullName;

    /**
     * Телефон отца студента.
     */
    private String fatherPhone;

    /**
     * Тип города студента.
     */
    private Integer cityType;

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @Deprecated
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    private String addressCountry;

    /**
     * Почтовый индекс адреса студента.
     */
    private String addressIndex;

    /**
     * Область адреса студента.
     */
    private String addressState;

    /**
     * Район адреса студента.
     */
    private String addressRegion;

    /**
     * Город адреса студента.
     */
    private String addressCity;

    /**
     * Улица адреса студента.
     */
    private String addressStreet;

    /**
     * Дом адреса студента.
     */
    private String addressHouse;

    /**
     * Корпус адреса студента.
     */
    private String addressHousePart;

    /**
     * Квартира адреса студента.
     */
    private String addressFlat;

    /**
     * Телефон студента.
     */
    private String phone;

    /**
     * Льготы студента.
     */
    private String privileges;

    /**
     * Является ли город студента деревней.
     */
    private boolean cityIsVillage;

    /**
     * Последний документ студента.
     */
    @JoinColumn(name = "last_document_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private V1DocumentModel lastDocument;

    /**
     * Последнее отклонение студента.
     */
    @JoinColumn(name = "last_deviation_id")
    @ManyToOne
    private V1DeviationModel lastDeviation;

    /**
     * Специализация студента.
     */
    @JoinColumn(name = "spez_id")
    @ManyToOne
    private V1SpecializationModel specialization;

    /**
     * Группа, к которой принадлежит студент.
     */
    @JoinColumn(name = "group_id")
    @ManyToOne
    @NotNull
    private V1GroupModel group;

    /**
     * Комната, в которой проживает студент.
     */
    @JoinColumn(name = "hostel_room_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    private V1HostelRoomModel hostelRoom;

    /**
     * Дата заселения студента.
     */
    private LocalDate checkInDate;

    /**
     * Дата выселения студента.
     */
    private LocalDate evictionDate;

    @Column(name = "approved", columnDefinition = "boolean default false")
    @NotNull
    private boolean isApproved = false;

    private String photoUrl;

}
