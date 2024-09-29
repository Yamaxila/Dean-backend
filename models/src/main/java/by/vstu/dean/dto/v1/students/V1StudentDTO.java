package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.hostels.V1HostelRoomDTO;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.models.students.StudentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link StudentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Студента")
public final class V1StudentDTO extends BaseDTO {

    /**
     * Фамилия студента.
     */
    @NotNull
    @Schema(title = "Фамилия")
    @ReflectionField(value = "lastName", clazz = StudentModel.class)
    private String surname;

    /**
     * Имя студента.
     */
    @NotNull
    @Schema(title = "Имя")
    @ReflectionField(value = "firstName", clazz = StudentModel.class)
    private String name;

    /**
     * Отчество студента.
     */
    @NotNull
    @Schema(title = "Отчество")
    @ReflectionField(value = "secondName", clazz = StudentModel.class)
    private String patronymic;

    /**
     * Пол студента.
     */
    @Schema(title = "Пол")
    @ReflectionField(value = "sex", clazz = StudentModel.class)
    private Integer sex;

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @Schema(title = "Адрес")
    @Deprecated
    @ReflectionField(value = "address", clazz = StudentModel.class)
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    @Schema(title = "Страна")
    @ReflectionField(value = "addressCountry", clazz = StudentModel.class)
    private String addressCountry;

    /**
     * Почтовый индекс адреса студента.
     */
    @Schema(title = "Почтовый индекс")
    @ReflectionField(value = "addressIndex", clazz = StudentModel.class)
    private String addressIndex;

    /**
     * Область адреса студента.
     */
    @Schema(title = "Область")
    @ReflectionField(value = "addressState", clazz = StudentModel.class)
    private String addressState;

    /**
     * Район адреса студента.
     */
    @Schema(title = "Район")
    @ReflectionField(value = "addressRegion", clazz = StudentModel.class)
    private String addressRegion;

    /**
     * Город адреса студента.
     */
    @Schema(title = "Город")
    @ReflectionField(value = "addressCity", clazz = StudentModel.class)
    private String addressCity;

    /**
     * Улица адреса студента.
     */
    @Schema(title = "Улица")
    @ReflectionField(value = "addressStreet", clazz = StudentModel.class)
    private String addressStreet;

    /**
     * Дом адреса студента.
     */
    @Schema(title = "Дом")
    @ReflectionField(value = "addressHouse", clazz = StudentModel.class)
    private String addressHouse;

    /**
     * Корпус адреса студента.
     */
    @Schema(title = "Корпус")
    @ReflectionField(value = "addressHousePart", clazz = StudentModel.class)
    private String addressHousePart;

    /**
     * Квартира адреса студента.
     */
    @Schema(title = "Квартира")
    @ReflectionField(value = "addressFlat", clazz = StudentModel.class)
    private String addressFlat;

    /**
     * Телефон студента.
     */
    @Schema(title = "Телефон")
    @ReflectionField(value = "phone", clazz = StudentModel.class)
    private String phone;

    /**
     * Специализация студента.
     */
    @Schema(title = "Специализация")
    private V1SpecializationDTO specialization;

    /**
     * Группа, к которой принадлежит студент.
     */
    @NotNull
    @NotBlank
    private V1GroupDTO group;

    /**
     * Комната, в которой проживает студент.
     */
    @Schema(title = "Комната")
    @JsonIgnore
    private V1HostelRoomDTO hostelRoom;

    @ReflectionField(clazz = StudentModel.class)
    private boolean approved;

}