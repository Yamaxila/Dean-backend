package by.vstu.dean.students.services;

import by.vstu.dean.services.AbsenceService;
import by.vstu.dean.students.dtos.StudentAbsenceDTO;
import by.vstu.dean.students.dtos.StudentAbsenceMonthDTO;
import by.vstu.dean.students.mappers.impl.V1StudentAbsenceMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentAbsenceService {

    private final V1StudentAbsenceMapperImpl v1StudentAbsenceMapperImpl;

    private final AbsenceService absenceService;

    private final StudentGradeService studentGradeService;

    public List<StudentAbsenceMonthDTO> findAbsencesByStudent() {
        Map<LocalDate, List<StudentAbsenceDTO>> map = this.v1StudentAbsenceMapperImpl
                .toDto(this.absenceService.getAbsenceByStudentCaseNo(Long.parseLong(studentGradeService.jwtCustomTokenDecoder("id_from_source"))))
                .stream().collect(Collectors.groupingBy(s -> s.getDate().withDayOfMonth(1), TreeMap::new, Collectors.toList()));

        List<StudentAbsenceMonthDTO> studentAbsenceMonthDTOS = new ArrayList<>();

        for (Map.Entry<LocalDate, List<StudentAbsenceDTO>> entry : map.entrySet()) {
            studentAbsenceMonthDTOS.add(new StudentAbsenceMonthDTO(entry.getKey().getYear(), entry.getKey().getMonth(), entry.getValue()));
        }
        return studentAbsenceMonthDTOS;
    }
}
