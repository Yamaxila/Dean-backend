package by.vstu.dean.old.models;

import by.vstu.dean.old.OldDBBaseModel;
import javax.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Dstudent", schema = "dbo")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dstudent_id", nullable = false))
})
public class DStudentModel extends OldDBBaseModel {
    @JoinColumn(name = "dfak_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DFacultyModel faculty;

    @JoinColumn(name = "dspecializ_id", nullable = false)
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DSpecializationModel specialization;

    @Column(name = "vtoroe")
    private Integer secondEnroll;

    @ManyToOne
    @JoinColumn(name = "gr", referencedColumnName = "gr")
    @NotFound(action = NotFoundAction.IGNORE)
    private DGroupModel group;

    @Column(name = "podgr")
    private Integer groupPart;

    @Column(name = "star")
    private Integer elder;

    @Column(name = "npp")
    private Integer listPart;

    @Column(name = "delo", nullable = false, length = 8)
    private String caseNo;

    @Column(name = "fam", nullable = false, length = 30)
    private String lastName;

    @Column(name = "name", length = 20)
    private String firstName;

    @Column(name = "otch", length = 20)
    private String secondName;

    @Column(name = "FIOLatin", length = 50)
    private String fullNameL;

    @Column(name = "nameLatin", length = 50)
    private String firstNameL;

    @Column(name = "pol", length = 8)
    private String sex;

    @Column(name = "graj", length = 20)
    private String citizenshipString;

    @Column(name = "dgraj_id")
    private Integer citizenship;

    @Column(name = "inostr", length = 20)
    private String studentLanguageString;

    @Column(name = "dinostr_id")
    private Integer studentLanguage;

    @Column(name = "data_roj")
    private LocalDateTime birthDate;

    @Column(name = "mesto_roj", length = 160)
    private String birthPlace;

    @Column(name = "okonchil", length = 160)
    private String educationString;

    @JoinColumn(name = "dyo_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DInstitutionModel institution;

    @Column(name = "god_okon")
    private Integer educationYearEnd;

    @Column(name = "sem_pol", length = 6)
    private String semSex; // СЕМЕЙНОЕ ПОЛОЖЕНИЕ

    @Column(name = "adres", length = 120)
    private String address;

    @Column(name = "CityType")
    private Integer cityType;

    @Column(name = "adres_city", length = 50)
    private String addressCity;

    @Column(name = "adres_indeks", length = 6)
    private String addressIndex;

    @Column(name = "adres_oblast", length = 50)
    private String addressState;

    @Column(name = "adres_raion", length = 50)
    private String addressRegion;

    @Column(name = "adres_gorod", length = 50)
    private String addressCity2;

    @Column(name = "adres_ylica", length = 50)
    private String addressStreet;

    @Column(name = "adres_dom", length = 50)
    private String addressHouse;

    @Column(name = "adres_korp", length = 20)
    private String addressHousePart;

    @Column(name = "adres_kv", length = 20)
    private String addressFlat;

    @Column(name = "CityType_reg")
    private Integer regCityType;

    @Column(name = "adres_city_reg", length = 50)
    private String regCity;

    @Column(name = "adres_indeks_reg", length = 6)
    private String regIndex;

    @Column(name = "adres_oblast_reg", length = 50)
    private String regState;

    @Column(name = "adres_raion_reg", length = 50)
    private String regRegion;

    @Column(name = "adres_gorod_reg", length = 50)
    private String regCity2;

    @Column(name = "adres_ylica_reg", length = 50)
    private String regStreet;

    @Column(name = "adres_dom_reg", length = 50)
    private String regHouse;

    @Column(name = "adres_korp_reg", length = 20)
    private String regHousePart;

    @Column(name = "adres_kv_reg", length = 20)
    private String regFlat;

    @Column(name = "telef", length = 60)
    private String phone;

    @Column(name = "rabota", length = 160)
    private String job;

    @Column(name = "staj")
    private Double jobExperience;

    @Column(name = "prikaz_zach", length = 10)
    private String enrollmentOrder;

    @Column(name = "data_zach")
    private LocalDateTime enrollmentDate;

    @Column(name = "otklon")
    private Integer deviationType;

    @Column(name = "pasport_serija", length = 6)
    private String passportSerial;

    @Column(name = "pasport_nomer", length = 10)
    private String passportNumber;

    @Column(name = "pasport_data_vid")
    private LocalDateTime passportIssueDate;

    @Column(name = "pasport_kem_vidan", length = 120)
    private String passportIssueString;

    @Column(name = "lichni_nomer", length = 20)
    private String passportId;

    @Column(name = "papa_fio", length = 60)
    private String fatherFullName;

    @Column(name = "papa_rabota", length = 200)
    private String fatherJob;

    @Column(name = "papa_telef", length = 60)
    private String fatherPhone;

    @Column(name = "mama_fio", length = 60)
    private String motherFullName;

    @Column(name = "mama_rabota", length = 200)
    private String motherJob;

    @Column(name = "mama_telef", length = 60)
    private String motherPhone;

    @Column(name = "gorod_selo", length = 10)
    private String cityIsVillage;

    @Column(name = "oplata_obychen", length = 20)
    private String paymentType;

    @Column(name = "lgoti")
    private String benefits;

    @Column(name = "obrazovanie", length = 50)
    private String education1;

    @Column(name = "dok_obraz", length = 20)
    private String education1DocumentType;

    @Column(name = "ser_dok_obraz", length = 10)
    private String education1DocumentSerial;

    @Column(name = "n_dok_obraz", length = 20)
    private String education1DocumentNumber;

    @Column(name = "konkyrsni_ball")
    private Double enrollSroce;

    @Column(name = "obschejitie", length = 10)
    private String hostel;

    @Column(name = "perepostypaet", length = 20)
    private String reEnroll;

    @Column(name = "fam_st", length = 20)
    private String lastSurname;

    @Column(name = "obrazovanie2", length = 50)
    private String education2;

    @Column(name = "dok_obraz2", length = 20)
    private String education2DocumentType;

    @Column(name = "ser_dok_obraz2", length = 10)
    private String education2DocumentSerial;

    @Column(name = "n_dok_obraz2", length = 20)
    private String education2DocumentNumber;

    @Column(name = "obrazovanie3", length = 50)
    private String education3;

    @Column(name = "dok_obraz3", length = 20)
    private String education3DocumentType;

    @Column(name = "ser_dok_obraz3", length = 10)
    private String education3DocumentSerial;

    @Column(name = "n_dok_obraz3", length = 20)
    private String education3DocumentNumber;

    @Column(name = "ocenki_vstup")
    private String enrollStudentScore;

    @Column(name = "nomer_stud", length = 50)
    private String studentNumber;

    @Column(name = "N_sam_Tryd", length = 10)
    private String unbound;

    @Column(name = "vipysknik")
    private Integer expelled;

    @Column(name = "gos_obespech")
    private Integer stateSupport;

    @Column(name = "perevesti")
    private Integer move;

    @Column(name = "inostranec")
    private Integer studentLanguageString2;

    @Column(name = "nom_dog", length = 20)
    private String documentNumber;

    @Column(name = "adres_email", length = 50)
    private String email;

    public boolean isExpelled() {
        return this.deviationType == 1
                || this.deviationType == 3
                || this.deviationType == 13
                || this.deviationType == 11
                || this.deviationType == 41;
    }

}