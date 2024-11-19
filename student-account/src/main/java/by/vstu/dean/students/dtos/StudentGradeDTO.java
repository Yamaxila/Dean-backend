package by.vstu.dean.students.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.models.merge.StatementStudentMerge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGradeDTO extends PublicDTO {
    private String grade;
    @ReflectionField(value = "statement.studyPlan.discipline.name", clazz = StatementStudentMerge.class)
    private String disciplineName;
    @ReflectionField(value = "passDate", clazz = StatementModel.class)
    private LocalDate dateOfLesson;
    private String teacherFIO;
    @ReflectionField(value = "statement.studyPlan.hours", clazz = StatementModel.class)
    private Integer hours;
    @ReflectionField(value = "statement.studyPlan.testPoints", clazz = StatementModel.class)
    private Double testPoints;
    @ReflectionField(value = "sheetNumber", clazz = StatementModel.class)
    private Integer examSheetNumber;
    @ReflectionField(value = "retake", clazz = StatementModel.class)
    private Boolean isRetake;
    private String classTopic;
}
