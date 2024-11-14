package by.vstu.migrate.v1.models.students;


import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.adapters.V1LocalDateTypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "documents")
public class V1DocumentModel extends V1DBBaseModel {

    private String fullNameL;
    private String firstNameL;
    private Long caseNo;
    @Deprecated
    private String citizenshipString;

    @JoinColumn(name = "citizenship")
    @ManyToOne
    private V1CitizenshipModel citizenship;
    @Deprecated
    private String studentLanguageString;
    @JoinColumn(name = "student_language")
    @ManyToOne
    private V1StudentLanguageModel studentLanguage;
    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate birthDate;
    private String birthPlace;
    private String educationString;

    @JoinColumn(name = "institution")
    @ManyToOne
    private V1InstitutionModel institution;
    private Integer educationYearEnd;
    private Integer regCityType;
    private String regCity;
    private String regIndex;
    private String regState;
    private String regRegion;
    private String regCity2;
    private String regStreet;
    private String regHouse;
    private String regHousePart;
    private String regFlat;

    private String job;
    private Double jobExperience;
    private String enrollmentOrder;

    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate enrollmentDate;
    private Integer deviationType;
    private String passportSerial;
    private String passportNumber;

    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate passportIssueDate;
    private String passportIssueString;
    private String passportId;
    private String fatherFullName;
    private String fatherJob;
    private String fatherPhone;
    private String motherFullName;
    private String motherJob;
    private String motherPhone;
    private String paymentType;
    private String benefits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "educations", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<V1EducationModel> educations;
    private Double enrollScore;
    private boolean needHostel;
    private boolean reEnroll;
    private String lastSurname;
    private String enrollStudentScore;
    private String studentNumber;
    private String unbound;
    private boolean stateSupport;
    private boolean move;
    private Long documentNumber;
    private String email;
    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate enrollDate;
    @JsonAdapter(V1LocalDateTypeAdapter.class)
    private LocalDate migrateDate;

}
