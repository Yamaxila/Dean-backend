package by.vstu.dean.future.models.students;


import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.CitizenshipModel;
import by.vstu.dean.future.models.InstitutionModel;
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
public class DocumentModel extends DBBaseModel {

    private String fullNameL;
    private String firstNameL;
    private Long caseNo;

    private String citizenshipString;
    @JoinColumn(name = "citizenship")
    @ManyToOne
    private CitizenshipModel citizenship;
    private String studentLanguageString;
    @JoinColumn(name = "studentLanguage")
    @ManyToOne
    private StudentLanguageModel studentLanguage;
    private LocalDate birthDate;
    private String birthPlace;
    private String educationString;

    @JoinColumn(name = "institution")
    @ManyToOne
    private InstitutionModel institution;
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
    private LocalDate enrollmentDate;
    private Integer deviationType;
    private String passportSerial;
    private String passportNumber;
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
    private List<EducationModel> educations;
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

    private LocalDate enrollDate;
    private LocalDate migrateDate;

}
