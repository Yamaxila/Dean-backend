package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.DocumentModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link by.vstu.dean.models.students.DocumentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentDTO extends BaseDTO {

    @ApiModelProperty(notes = "Полное имя(латиница)")
    @ReflectionField(clazz = DocumentModel.class)
    private String fullNameL;
    @ApiModelProperty(notes = "Имя(Латиница)")
    @ReflectionField(clazz = DocumentModel.class)
    private String firstNameL;
    @ApiModelProperty(notes = "Номер договора/Студенческого")
    @ReflectionField(clazz = DocumentModel.class)
    private Long caseNo;
    @JoinColumn(name = "citizenship")
    @ManyToOne
    @ApiModelProperty(notes = "Гражданство")
    @ReflectionField(clazz = DocumentModel.class)
    private CitizenshipDTO citizenship;
    @ApiModelProperty(notes = "Иностранный язык")
    @ReflectionField(clazz = DocumentModel.class)
    private StudentLanguageDTO studentLanguage;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата рождения")
    @ReflectionField(clazz = DocumentModel.class)
    private LocalDate birthDate;
    @ApiModelProperty(notes = "Образование")
    @ReflectionField(clazz = DocumentModel.class)
    private String educationString;
    @ApiModelProperty(notes = "Последнее место учебы")
    @ReflectionField(clazz = DocumentModel.class)
    private InstitutionDTO institution;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата зачисления")
    @ReflectionField(clazz = DocumentModel.class)
    private LocalDate enrollmentDate;
    @ApiModelProperty(notes = "Образования")
    private List<EducationDTO> educations;
    @ApiModelProperty(notes = "Балл при зачислении")
    @ReflectionField(clazz = DocumentModel.class)
    private Double enrollScore;
    @ApiModelProperty(notes = "Необходимо общежитие")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean needHostel;
    @ApiModelProperty(notes = "Перепоступает")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean reEnroll;
    @ApiModelProperty(notes = "Девичья фамилия(Если менялась)")
    @ReflectionField(clazz = DocumentModel.class)
    private String lastSurname;
    @ApiModelProperty(notes = "???")
    @ReflectionField(clazz = DocumentModel.class)
    private String enrollStudentScore;
    @ApiModelProperty(notes = "Поддержка государством")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean stateSupport;
    @ApiModelProperty(notes = "Переведен")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean move;
    @ApiModelProperty(notes = "E-mail")
    @ReflectionField(clazz = DocumentModel.class)
    private String email;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @ApiModelProperty(notes = "Дата зачисления")
    @ReflectionField(clazz = DocumentModel.class)
    private LocalDate enrollDate;

}
