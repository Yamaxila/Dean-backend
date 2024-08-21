package by.vstu.dean.models.students;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.specs.SpecializationModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@ApiModel(description = "Объект студента")
public class StudentModel extends DBBaseModel {

    /**
     * Фамилия студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия")
    private String lastName;

    /**
     * Имя студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя")
    private String firstName;

    /**
     * Отчество студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество")
    private String secondName;

    /**
     * Пол студента.
     */
    @ApiModelProperty(notes = "Пол")
    private Integer sex;

    /**
     * Тип города студента.
     */
    @ApiModelProperty(notes = "Тип города")
    private Integer cityType;

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @ApiModelProperty(notes = "Адрес")
    @Deprecated
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    @ApiModelProperty(notes = "Страна")
    private String addressCountry;

    /**
     * Почтовый индекс адреса студента.
     */
    @ApiModelProperty(notes = "Почтовый индекс")
    private String addressIndex;

    /**
     * Область адреса студента.
     */
    @ApiModelProperty(notes = "Область")
    private String addressState;

    /**
     * Район адреса студента.
     */
    @ApiModelProperty(notes = "Район")
    private String addressRegion;

    /**
     * Город адреса студента.
     */
    @ApiModelProperty(notes = "Город")
    private String addressCity;

    /**
     * Улица адреса студента.
     */
    @ApiModelProperty(notes = "Улица")
    private String addressStreet;

    /**
     * Дом адреса студента.
     */
    @ApiModelProperty(notes = "Дом")
    private String addressHouse;

    /**
     * Корпус адреса студента.
     */
    @ApiModelProperty(notes = "Корпус")
    private String addressHousePart;

    /**
     * Квартира адреса студента.
     */
    @ApiModelProperty(notes = "Квартира")
    private String addressFlat;

    /**
     * Телефон студента.
     */
    @ApiModelProperty(notes = "Телефон")
    private String phone;

    /**
     * Льготы студента.
     */
    @ApiModelProperty(notes = "Льготы")
    private String benefits;


    /**
     * Является ли город студента деревней.
     */
    @ApiModelProperty(notes = "Является ли город - деревней")
    private boolean cityIsVillage;

    /**
     * Последний документ студента.
     */
    @JoinColumn(name = "last_document_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    @ApiModelProperty(notes = "Последний документ")
    private DocumentModel lastDocument;

    /**
     * Последнее отклонение студента.
     */
    @JoinColumn(name = "last_deviation_id")
    @ManyToOne
    @ApiModelProperty(notes = "Последнее отклонение")
    private DeviationModel lastDeviation;

    /**
     * Специализация студента.
     */
    @JoinColumn(name = "spez_id")
    @ManyToOne
    @ApiModelProperty(notes = "Специализация")
    private SpecializationModel specialization;

    /**
     * Группа, к которой принадлежит студент.
     */
    @JoinColumn(name = "group_id")
    @ManyToOne
    @NotNull
    @ApiModelProperty(notes = "Группа")
    private GroupModel group;

    /**
     * Комната, в которой проживает студент.
     */
    @JoinColumn(name = "hostel_room_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(notes = "Комната")
    @JsonBackReference
    private HostelRoomModel hostelRoom;

    /**
     * Дата заселения студента.
     */
    @ApiModelProperty(notes = "Дата заселения")
    private LocalDate checkInDate;

    /**
     * Дата выселения студента.
     */
    @ApiModelProperty(notes = "Дата выселения")
    private LocalDate evictionDate;

    @Column(name = "approved", columnDefinition = "boolean default false")
    @NotNull
    private boolean isApproved = false;
}
