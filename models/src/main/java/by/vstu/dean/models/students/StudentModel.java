package by.vstu.dean.models.students;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EPaymentType;
import by.vstu.dean.models.changes.PenaltyModel;
import by.vstu.dean.models.changes.StudentChangeModel;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.students.internal.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий объект студента.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dean_students")
@Schema(title = "Модель студента")
@Transactional
public class StudentModel extends DBBaseModel {

    /**
     * Фамилия студента.
     */
    @NotNull
    @Schema(title = "Фамилия")
    private String lastName;

    /**
     * Имя студента.
     */
    @NotNull
    @Schema(title = "Имя")
    private String firstName;

    /**
     * Отчество студента.
     */
    @NotNull
    @Schema(title = "Отчество")
    private String secondName;

    @Schema(title = "Тип оплаты")
    @Enumerated(EnumType.ORDINAL)
    private EPaymentType paymentType;

    /**
     * Пол студента.
     */
    @Schema(title = "Пол")
    private Integer sex;

    /**
     * Телефон студента.
     */
    @Schema(title = "Телефон")
    @JoinColumn(name = "phone_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PhoneModel phone;

    @Schema(title = "E-mail")
    private String email;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата рождения")
    @NotNull
    private LocalDate birthDate;

    @Schema(title = "Образование")
    @NotNull
    @Deprecated
    private String educationString; //TODO: По факту, это тоже бред. Наверное, нужно заменить на enum

    @JoinColumn(name = "institution")
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(title = "Последнее место учебы")
    private InstitutionModel institution;
    @Schema(title = "Год окончания")
    private Integer educationYearEnd;

    @Schema(title = "Работа")
    private String job;
    @Schema(title = "Стаж")
    private Double jobExperience;

    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата зачисления")
    private LocalDate enrollmentDate;

    @Schema(title = "Перепоступает")
    private boolean reEnroll;

    @Schema(title = "Свободный диплом")
    private String unbound;
    @Schema(title = "Поддержка государством")
    private boolean stateSupport;
    @Schema(title = "Переведен")
    private boolean move;


    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата зачисления")
    private LocalDate enrollDate;

    @Schema(title = "Полное имя(латиница)")
    @NotNull
    private String fullNameL; // Это нужно разбивать на фамилию, имя и отчество
    @Schema(title = "Имя(Латиница)")
    @NotNull
    private String firstNameL;

    @Schema(title = "Девичья фамилия")
    private String maidenName;

    @Schema(title = "Номер договора/Студенческого")
    private Long caseNo;
    @Schema(title = "Номер договора (2)")
    private Long documentNumber; //TODO: Какой-то бред

    /**
     * Адрес студента.
     */
    @Schema(title = "Адрес")
    @JoinColumn(name = "address_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddressModel address;

    /**
     * Прописка студента.
     */
    @Schema(title = "Прописка")
    @JoinColumn(name = "reg_address_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddressModel regAddress;

    @JoinColumn(name = "citizenship_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(title = "Гражданство")
    @NotNull
    private CitizenshipModel citizenship;

    @JoinColumn(name = "student_language_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(title = "Иностранный язык")
    @NotNull
    private StudentLanguageModel studentLanguage;

    @JoinColumn(name = "passport_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PassportModel passport;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dean_parents", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @Schema(title = "Родители/опекуны")
    private List<ParentModel> parents;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dean_educations", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @Schema(title = "Образования")
    private List<EducationModel> educations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dean_student_changes", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @Schema(title = "Образования")
    private List<StudentChangeModel> changes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dean_penalties", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @Schema(title = "Образования")
    private List<PenaltyModel> penalties;

    @Schema(title = "Место рождения")
    @NotNull
    private String birthPlace;

    /**
     * Льготы студента.
     */
    @Schema(title = "Льготы")
    private String benefits;

    /**
     * Специализация студента.
     */
    @JoinColumn(name = "spez_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(title = "Специализация")
    private SpecializationModel specialization;

    /**
     * Группа, к которой принадлежит студент.
     */
    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @Schema(title = "Группа")
    private GroupModel group;

    @Schema(title = "Необходимо общежитие")
    private boolean needHostel;

    /**
     * Комната, в которой проживает студент.
     */
    @JoinColumn(name = "hostel_room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Schema(title = "Комната")
    @JsonBackReference
    private HostelRoomModel hostelRoom;

    /**
     * Дата заселения студента.
     */
    @Schema(title = "Дата заселения")
    private LocalDate checkInDate;

    /**
     * Дата выселения студента.
     */
    @Schema(title = "Дата выселения")
    private LocalDate evictionDate;

    @Column(name = "approved", columnDefinition = "boolean default false")
    @NotNull
    private boolean approved;

    /**
     * URL-адрес фото студента.
     */
    @Schema(title = "URL-адрес фото студента")
    private String photoUrl;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StudentModel that = (StudentModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
