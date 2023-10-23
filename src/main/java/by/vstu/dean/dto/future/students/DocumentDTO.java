package by.vstu.dean.dto.future.students;

import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.dto.BaseDTO;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link by.vstu.dean.future.models.students.DocumentModel}
 */
@Data
public class DocumentDTO extends BaseDTO {

    @ApiModelProperty(notes = "Полное имя(латиница)")
    private String fullNameL;
    @ApiModelProperty(notes = "Имя(Латиница)")
    private String firstNameL;
    @ApiModelProperty(notes = "Номер договора/Студенческого")
    private Long caseNo;
    @JoinColumn(name = "citizenship")
    @ManyToOne
    @ApiModelProperty(notes = "Гражданство")
    private CitizenshipDTO citizenship;
    @ApiModelProperty(notes = "Иностранный язык")
    private StudentLanguageDTO studentLanguage;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата рождения")
    private LocalDate birthDate;
    @ApiModelProperty(notes = "Образование")
    private String educationString;
    @ApiModelProperty(notes = "Последнее место учебы")
    private InstitutionDTO institution;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата зачисления")
    private LocalDate enrollmentDate;
    @ApiModelProperty(notes = "Образования")
    private List<EducationDTO> educations;
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
    @ApiModelProperty(notes = "Поддержка государством")
    private boolean stateSupport;
    @ApiModelProperty(notes = "Переведен")
    private boolean move;
    @ApiModelProperty(notes = "E-mail")
    private String email;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата зачисления")
    private LocalDate enrollDate;

}
