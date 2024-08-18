package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.students.StudentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for {@link StudentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class StudentDTO extends BaseDTO {

    /**
     * Фамилия студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия")
    private String surname;

    /**
     * Имя студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя")
    private String name;

    /**
     * Отчество студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество")
    private String patronymic;

    /**
     * Пол студента.
     */
    @ApiModelProperty(notes = "Пол")
    private Integer sex;

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
     * Специализация студента.
     */
    @ApiModelProperty(notes = "Специализация")
    private SpecializationDTO specialization;

    /**
     * Группа, к которой принадлежит студент.
     */
    @NotNull
    @NotBlank
    private GroupDTO group;

    /**
     * Комната, в которой проживает студент.
     */
    @ApiModelProperty(notes = "Комната")
    @JsonIgnore
    private HostelRoomModel hostelRoom;

    private boolean isApproved;

}