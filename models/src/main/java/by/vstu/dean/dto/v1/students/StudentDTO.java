package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.hostels.HostelRoomDTO;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
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
    @ReflectionField(value = "lastName", clazz = StudentModel.class)
    private String surname;

    /**
     * Имя студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя")
    @ReflectionField(value = "firstName", clazz = StudentModel.class)
    private String name;

    /**
     * Отчество студента.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество")
    @ReflectionField(value = "secondName", clazz = StudentModel.class)
    private String patronymic;

    /**
     * Пол студента.
     */
    @ApiModelProperty(notes = "Пол")
    @ReflectionField(value = "sex", clazz = StudentModel.class)
    private Integer sex;

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @ApiModelProperty(notes = "Адрес")
    @Deprecated
    @ReflectionField(value = "address", clazz = StudentModel.class)
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    @ApiModelProperty(notes = "Страна")
    @ReflectionField(value = "addressCountry", clazz = StudentModel.class)
    private String addressCountry;

    /**
     * Почтовый индекс адреса студента.
     */
    @ApiModelProperty(notes = "Почтовый индекс")
    @ReflectionField(value = "addressIndex", clazz = StudentModel.class)
    private String addressIndex;

    /**
     * Область адреса студента.
     */
    @ApiModelProperty(notes = "Область")
    @ReflectionField(value = "addressState", clazz = StudentModel.class)
    private String addressState;

    /**
     * Район адреса студента.
     */
    @ApiModelProperty(notes = "Район")
    @ReflectionField(value = "addressRegion", clazz = StudentModel.class)
    private String addressRegion;

    /**
     * Город адреса студента.
     */
    @ApiModelProperty(notes = "Город")
    @ReflectionField(value = "addressCity", clazz = StudentModel.class)
    private String addressCity;

    /**
     * Улица адреса студента.
     */
    @ApiModelProperty(notes = "Улица")
    @ReflectionField(value = "addressStreet", clazz = StudentModel.class)
    private String addressStreet;

    /**
     * Дом адреса студента.
     */
    @ApiModelProperty(notes = "Дом")
    @ReflectionField(value = "addressHouse", clazz = StudentModel.class)
    private String addressHouse;

    /**
     * Корпус адреса студента.
     */
    @ApiModelProperty(notes = "Корпус")
    @ReflectionField(value = "addressHousePart", clazz = StudentModel.class)
    private String addressHousePart;

    /**
     * Квартира адреса студента.
     */
    @ApiModelProperty(notes = "Квартира")
    @ReflectionField(value = "addressFlat", clazz = StudentModel.class)
    private String addressFlat;

    /**
     * Телефон студента.
     */
    @ApiModelProperty(notes = "Телефон")
    @ReflectionField(value = "phone", clazz = StudentModel.class)
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
    private HostelRoomDTO hostelRoom;

    @ReflectionField(clazz = StudentModel.class)
    private boolean approved;

}