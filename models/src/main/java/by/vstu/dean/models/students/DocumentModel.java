package by.vstu.dean.models.students;


import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "documents")
@ApiModel(description = "Объект документа/договора студента")
public class DocumentModel extends DBBaseModel {

    @ApiModelProperty(notes = "Полное имя(латиница)")
    private String fullNameL;
    @ApiModelProperty(notes = "Имя(Латиница)")
    private String firstNameL;
    @ApiModelProperty(notes = "Номер договора/Студенческого")
    private Long caseNo;
    @ApiModelProperty(notes = "Гражданство")
    @Deprecated
    private String citizenshipString;

    @JoinColumn(name = "citizenship")
    @ManyToOne
    @ApiModelProperty(notes = "Гражданство")
    private CitizenshipModel citizenship;
    @ApiModelProperty(notes = "Иностранный язык")
    @Deprecated
    private String studentLanguageString;
    @JoinColumn(name = "student_language")
    @ManyToOne
    @ApiModelProperty(notes = "Иностранный язык")
    private StudentLanguageModel studentLanguage;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата рождения")
    private LocalDate birthDate;
    @ApiModelProperty(notes = "Место рождения")
    private String birthPlace;
    @ApiModelProperty(notes = "Образование")
    private String educationString;

    @JoinColumn(name = "institution")
    @ManyToOne
    @ApiModelProperty(notes = "Последнее место учебы")
    private InstitutionModel institution;
    @ApiModelProperty(notes = "Год окончания")
    private Integer educationYearEnd;
    @ApiModelProperty(notes = "Тип города прописки")
    private Integer regCityType;
    @ApiModelProperty(notes = "Город прописки")
    private String regCity;
    @ApiModelProperty(notes = "Индекс прописки")
    private String regIndex;
    @ApiModelProperty(notes = "Область прописки")
    private String regState;
    @ApiModelProperty(notes = "Регион прописки")
    private String regRegion;
    @ApiModelProperty(notes = "Город прописки (2)???")
    private String regCity2;
    @ApiModelProperty(notes = "Улица прописки")
    private String regStreet;
    @ApiModelProperty(notes = "Дом прописки")
    private String regHouse;
    @ApiModelProperty(notes = "Корпус прописки")
    private String regHousePart;
    @ApiModelProperty(notes = "Квартира прописки")
    private String regFlat;

    @ApiModelProperty(notes = "Работа")
    private String job;
    @ApiModelProperty(notes = "Стаж")
    private Double jobExperience;
    @ApiModelProperty(notes = "???")
    private String enrollmentOrder;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата зачисления")
    private LocalDate enrollmentDate;
    @ApiModelProperty(notes = "Тип отклонения")
    private Integer deviationType;
    @ApiModelProperty(notes = "Серия паспорта")
    private String passportSerial;
    @ApiModelProperty(notes = "Номер паспорта")
    private String passportNumber;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата выдачи паспорта")
    private LocalDate passportIssueDate;
    @ApiModelProperty(notes = "Кем выдан паспорт")
    private String passportIssueString;
    @ApiModelProperty(notes = "ID паспорта")
    private String passportId;
    @ApiModelProperty(notes = "Полное имя отца")
    private String fatherFullName;
    @ApiModelProperty(notes = "Работа отца")
    private String fatherJob;
    @ApiModelProperty(notes = "Телефон отца")
    private String fatherPhone;
    @ApiModelProperty(notes = "Полное имя матери")
    private String motherFullName;
    @ApiModelProperty(notes = "Работа матери")
    private String motherJob;
    @ApiModelProperty(notes = "Телефон")
    private String motherPhone;
    @ApiModelProperty(notes = "Тип оплаты")
    private String paymentType;
    @ApiModelProperty(notes = "Льготы")
    private String benefits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "educations", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @ApiModelProperty(notes = "Образования")
    private List<EducationModel> educations;
    @ApiModelProperty(notes = "Балл при зачислении")
    private Double enrollScore;
    @ApiModelProperty(notes = "Необходимо общежитие")
    private boolean needHostel;
    @ApiModelProperty(notes = "Перепоступает")
    private boolean reEnroll;
    @ApiModelProperty(notes = "Девичья фамилия(Если менялась)")
    private String lastSurname;
    @ApiModelProperty(notes = "???")
    private String enrollStudentScore;
    @ApiModelProperty(notes = "Норме студента")
    private String studentNumber;
    @ApiModelProperty(notes = "Свободный диплом")
    private String unbound;
    @ApiModelProperty(notes = "Поддержка государством")
    private boolean stateSupport;
    @ApiModelProperty(notes = "Переведен")
    private boolean move;
    @ApiModelProperty(notes = "Номер документа")
    private Long documentNumber;
    @ApiModelProperty(notes = "E-mail")
    private String email;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата зачисления")
    private LocalDate enrollDate;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата миграции")
    private LocalDate migrateDate;

}
