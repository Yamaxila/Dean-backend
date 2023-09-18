package by.vstu.dean.future.models;


import by.vstu.dean.future.DBBaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String foreignerString;
    @JoinColumn(name = "foreigner")
    @ManyToOne
    private ForeignerModel foreigner;
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
    private String education1;
    private String education1DocumentType;
    private String education1DocumentSerial;
    private String education1DocumentNumber;
    private Double enrollScore;
    private boolean needHostel;
    private boolean reEnroll;
    private String lastSurname;
    private String education2;
    private String education2DocumentType;
    private String education2DocumentSerial;
    private String education2DocumentNumber;
    private String education3;
    private String education3DocumentType;
    private String education3DocumentSerial;
    private String education3DocumentNumber;
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
