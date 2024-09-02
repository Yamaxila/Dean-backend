package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.adapters.LocalDateTypeAdapter;
import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.DocumentModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link by.vstu.dean.models.students.DocumentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentDTO extends BaseDTO {

    @Schema(title = "Полное имя(латиница)")
    @ReflectionField(clazz = DocumentModel.class)
    private String fullNameL;
    @Schema(title = "Имя(Латиница)")
    @ReflectionField(clazz = DocumentModel.class)
    private String firstNameL;
    @Schema(title = "Номер договора/Студенческого")
    @ReflectionField(clazz = DocumentModel.class)
    private Long caseNo;
    @JoinColumn(name = "citizenship")
    @ManyToOne
    @Schema(title = "Гражданство")
    @ReflectionField(clazz = DocumentModel.class)
    private CitizenshipDTO citizenship;
    @Schema(title = "Иностранный язык")
    @ReflectionField(clazz = DocumentModel.class)
    private StudentLanguageDTO studentLanguage;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата рождения")
    @ReflectionField(clazz = DocumentModel.class)
    private LocalDate birthDate;
    @Schema(title = "Образование")
    @ReflectionField(clazz = DocumentModel.class)
    private String educationString;
    @Schema(title = "Последнее место учебы")
    @ReflectionField(clazz = DocumentModel.class)
    private InstitutionDTO institution;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата зачисления")
    @ReflectionField(clazz = DocumentModel.class)
    private LocalDate enrollmentDate;
    @Schema(title = "Образования")
    private List<EducationDTO> educations;
    @Schema(title = "Балл при зачислении")
    @ReflectionField(clazz = DocumentModel.class)
    private Double enrollScore;
    @Schema(title = "Необходимо общежитие")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean needHostel;
    @Schema(title = "Перепоступает")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean reEnroll;
    @Schema(title = "Девичья фамилия(Если менялась)")
    @ReflectionField(clazz = DocumentModel.class)
    private String lastSurname;
    @Schema(title = "???")
    @ReflectionField(clazz = DocumentModel.class)
    private String enrollStudentScore;
    @Schema(title = "Поддержка государством")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean stateSupport;
    @Schema(title = "Переведен")
    @ReflectionField(clazz = DocumentModel.class)
    private boolean move;
    @Schema(title = "E-mail")
    @ReflectionField(clazz = DocumentModel.class)
    private String email;
    @JsonAdapter(LocalDateTypeAdapter.class)
    @Schema(title = "Дата зачисления")
    @ReflectionField(clazz = DocumentModel.class)
    private LocalDate enrollDate;

}
