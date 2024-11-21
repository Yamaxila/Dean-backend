package by.vstu.dean.students.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StudentGradeAvgDTO {
    private Double average;
    private List<StudentGradeDTO> grades;
}
