package by.vstu.dean.students.dtos;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class StudentGradeAvgDTO {
    private Double average;
    private List<StudentGradeDTO> grades;
}
