package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.hostels.HostelRoomModel;
import by.vstu.dean.future.models.specs.SpecializationModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Класс, представляющий объект студента.
 */
@Entity
@Setter
@Getter
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
     * Является ли город студента деревней.
     */
    @ApiModelProperty(notes = "Является ли город - деревней")
    private boolean cityIsVillage;

    /**
     * Последний документ студента.
     */
    @JoinColumn(name = "last_document_id")
    @ManyToOne(cascade = CascadeType.ALL)
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
    @JsonIgnore
    private HostelRoomModel hostelRoom;

    @Column(name = "approved")
    @NotNull
    private boolean isApproved = false;
}
