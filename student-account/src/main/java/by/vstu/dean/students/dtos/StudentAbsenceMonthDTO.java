package by.vstu.dean.students.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Month;
import java.util.List;

@Data
@AllArgsConstructor
public class StudentAbsenceMonthDTO {
    private Integer year;
    private Month month;
    private List<StudentAbsenceDTO> absencesDTos;
}
