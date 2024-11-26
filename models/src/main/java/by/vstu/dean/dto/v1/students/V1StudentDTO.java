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
    @ReflectionField(clazz = StudentModel.class)
    private String surname;

    /**
     * Имя студента.
     */
    @NotNull
    @Schema(title = "Имя")
    @ReflectionField(clazz = StudentModel.class)
    private String name;

    /**
     * Отчество студента.
     */
    @NotNull
    @Schema(title = "Отчество")
    @ReflectionField(clazz = StudentModel.class)
    private String patronymic;

    /**
     * Пол студента.
     */
    @Schema(title = "Пол")
    private String sex;

    /**
     * Номер зачетки студента.
     */
    @Schema(title = "Номер зачетки студента")
    @ReflectionField(value = "caseNo", clazz = StudentModel.class)
    private String caseNo;

    /**
     * Адрес студента (устаревшее поле, используйте отдельные поля для адреса).
     */
    @Schema(title = "Адрес")
    @Deprecated
    @ReflectionField(value = "address.address", clazz = StudentModel.class)
    private String address;

    /**
     * Страна, в которой проживает студент.
     */
    @Schema(title = "Страна")
    @ReflectionField(value = "address.country", clazz = StudentModel.class)
    private String addressCountry;

    /**
     * Почтовый индекс адреса студента.
     */
    @Schema(title = "Почтовый индекс")
    @ReflectionField(value = "address.postIndex", clazz = StudentModel.class)
    private String addressIndex;

    /**
     * Область адреса студента.
     */
    @Schema(title = "Область")
    @ReflectionField(value = "address.state", clazz = StudentModel.class)
    private String addressState;

    /**
     * Район адреса студента.
     */
    @Schema(title = "Район")
    @ReflectionField(value = "address.region", clazz = StudentModel.class)
    private String addressRegion;

    /**
     * Город адреса студента.
     */
    @Schema(title = "Город")
    @ReflectionField(value = "address.city", clazz = StudentModel.class)
    private String addressCity;

    /**
     * Улица адреса студента.
     */
    @Schema(title = "Улица")
    @ReflectionField(value = "address.street", clazz = StudentModel.class)
    private String addressStreet;

    /**
     * Дом адреса студента.
     */
    @Schema(title = "Дом")
    @ReflectionField(value = "address.house", clazz = StudentModel.class)
    private String addressHouse;

    /**
     * Корпус адреса студента.
     */
    @Schema(title = "Корпус")
    @ReflectionField(value = "address.housePart", clazz = StudentModel.class)
    private String addressHousePart;

    /**
     * Квартира адреса студента.
     */
    @Schema(title = "Квартира")
    @ReflectionField(value = "address.flat", clazz = StudentModel.class)
    private String addressFlat;

    /**
     * Телефон студента.
     */
    @Schema(title = "Телефон")
    @ReflectionField(value = "phone.phone", clazz = StudentModel.class)
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

    @ReflectionField(clazz = StudentModel.class)
    private String photoUrl;


}