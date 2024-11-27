package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.hostels.V1HostelRoomDTO;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.models.students.internal.ParentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

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
     * Место рождения студента.
     */
    @NotNull
    @Schema(title = "Место рождения")
    @ReflectionField(clazz = StudentModel.class)
    private String birthPlace;

    /**
     * Пол студента.
     */
    @Schema(title = "Пол")
    private String sex;

    /**
     * Последнее место учебы студента.
     */
    @Schema(title = "Последнее место учебы")
    private V1InstitutionDTO institution;

    /**
     * Год окончания последнего места учебы студента.
     */
    @Schema(title = "Год окончания последнего места учебы")
    @ReflectionField(clazz = StudentModel.class)
    private Integer educationYearEnd;

    /**
     * Гражданство студента.
     */
    @Schema(title = "Гражданство")
    private V1CitizenshipDTO citizenship;

    /**
     * Иностранный язык студента.
     */
    @Schema(title = "Иностранный язык")
    private V1StudentLanguageDTO language;

    /**
     * Номер зачетки студента.
     */
    @Schema(title = "Номер зачетки студента")
    @ReflectionField(value = "caseNo", clazz = StudentModel.class)
    private Long caseNo;

    /**
     * Тип оплаты обучения студента.
     */
    @Schema(title = "Тип оплаты обучения студента")
    @ReflectionField(value = "paymentType.name", clazz = StudentModel.class)
    private String paymentType;

    /**
     * Дата зачисления студента.
     */
    @Schema(title = "Дата зачисления")
    @ReflectionField(value = "enrollDate", clazz = StudentModel.class)
    private LocalDate enrollDate;

    /**
     * Дата рождения студента.
     */
    @Schema(title = "Дата рождения")
    @ReflectionField(value = "birthDate", clazz = StudentModel.class)
    private LocalDate birthDate;

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
     * E-mail студента..
     */
    @Schema(title = "E-mail")
    private String email;

    /**
     * Оператор мобильного телефона студента.
     */
    @ReflectionField(value = "phone.type.name", clazz = ParentModel.class)
    private String phoneType;

    /**
     * Работа студента.
     */
    @Schema(title = "Работа")
    @ReflectionField(clazz = StudentModel.class)
    private String job;

    /**
     * Стаж работы студента.
     */
    @Schema(title = "Стаж")
    @ReflectionField(clazz = StudentModel.class)
    private Double jobExperience;

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

    /**
     * Образования студента
     */
    @Schema(title = "Образования студента")
    private List<V1EducationDTO> educations;

    /**
     * Родители студента
     */
    @Schema(title = "Родители студента")
    private List<V1ParentDTO> parents;

    /**
     * Утверждено ли заселение студента.
     */
    @Schema(title = "Утверждено ли заселение студента")
    @ReflectionField(clazz = StudentModel.class)
    private Boolean approved;

    /**
     * URL-адрес фото студента.
     */
    @Schema(title = "URL-адрес фото студента")
    @ReflectionField(clazz = StudentModel.class)
    private String photoUrl;


}