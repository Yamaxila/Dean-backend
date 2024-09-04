package by.vstu.dean.models.students;


import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "documents")
@Schema(title = "Модель документа/договора студента")
public class DocumentModel extends DBBaseModel {

    @Schema(title = "Полное имя(латиница)")
    @NotNull
    private String fullNameL;
    @Schema(title = "Имя(Латиница)")
    @NotNull
    private String firstNameL;
    @Schema(title = "Номер договора/Студенческого")
    private Long caseNo;
    @Schema(title = "Гражданство")
    @Deprecated
    private String citizenshipString;

    @JoinColumn(name = "citizenship")
    @ManyToOne
    @Schema(title = "Гражданство")
    @NotNull
    private CitizenshipModel citizenship;
    @Schema(title = "Иностранный язык")
    @Deprecated
    private String studentLanguageString;
    @JoinColumn(name = "student_language")
    @ManyToOne
    @Schema(title = "Иностранный язык")
    @NotNull
    private StudentLanguageModel studentLanguage;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата рождения")
    @NotNull
    private LocalDate birthDate;
    @Schema(title = "Место рождения")
    @NotNull
    private String birthPlace;
    @Schema(title = "Образование")
    @NotNull
    private String educationString;

    @JoinColumn(name = "institution")
    @ManyToOne
    @Schema(title = "Последнее место учебы")
    private InstitutionModel institution;
    @Schema(title = "Год окончания")
    private Integer educationYearEnd;
    @Schema(title = "Тип города прописки")
    private Integer regCityType;
    @Schema(title = "Город прописки")
    private String regCity;
    @Schema(title = "Индекс прописки")
    private String regIndex;
    @Schema(title = "Область прописки")
    private String regState;
    @Schema(title = "Регион прописки")
    private String regRegion;
    @Schema(title = "Город прописки (2)???")
    private String regCity2;
    @Schema(title = "Улица прописки")
    private String regStreet;
    @Schema(title = "Дом прописки")
    private String regHouse;
    @Schema(title = "Корпус прописки")
    private String regHousePart;
    @Schema(title = "Квартира прописки")
    private String regFlat;

    @Schema(title = "Работа")
    private String job;
    @Schema(title = "Стаж")
    private Double jobExperience;
    @Schema(title = "???")
    private String enrollmentOrder;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата зачисления")
    private LocalDate enrollmentDate;
    @Schema(title = "Тип отклонения")
    private Integer deviationType;
    @Schema(title = "Серия паспорта")
    @NotNull
    private String passportSerial;
    @Schema(title = "Номер паспорта")
    @NotNull
    private String passportNumber;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата выдачи паспорта")
    @NotNull
    private LocalDate passportIssueDate;
    @Schema(title = "Кем выдан паспорт")
    @NotNull
    private String passportIssueString;
    @Schema(title = "ID паспорта")
    @NotNull
    private String passportId;
    @Schema(title = "Полное имя отца")
    private String fatherFullName;
    @Schema(title = "Работа отца")
    private String fatherJob;
    @Schema(title = "Телефон отца")
    private String fatherPhone;
    @Schema(title = "Полное имя матери")
    private String motherFullName;
    @Schema(title = "Работа матери")
    private String motherJob;
    @Schema(title = "Телефон")
    private String motherPhone;
    @Schema(title = "Тип оплаты")
    private String paymentType;
    @Schema(title = "Льготы")
    private String benefits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "educations", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @Schema(title = "Образования")
    private List<EducationModel> educations;
    @Schema(title = "Балл при зачислении")
    private Double enrollScore;
    @Schema(title = "Необходимо общежитие")
    private boolean needHostel;
    @Schema(title = "Перепоступает")
    private boolean reEnroll;
    @Schema(title = "Девичья фамилия(Если менялась)")
    private String lastSurname;
    @Schema(title = "???")
    private String enrollStudentScore;
    @Schema(title = "Номер студента по списку")
    private String studentNumber;
    @Schema(title = "Свободный диплом")
    private String unbound;
    @Schema(title = "Поддержка государством")
    private boolean stateSupport;
    @Schema(title = "Переведен")
    private boolean move;
    @Schema(title = "Номер документа")
    private Long documentNumber;
    @Schema(title = "E-mail")
    private String email;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата зачисления")
    private LocalDate enrollDate;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата миграции")
    private LocalDate migrateDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DocumentModel that = (DocumentModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
