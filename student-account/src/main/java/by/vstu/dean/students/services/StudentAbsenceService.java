package by.vstu.dean.students.services;

import by.vstu.dean.services.AbsenceService;
import by.vstu.dean.students.dtos.StudentAbsenceDTO;
import by.vstu.dean.students.dtos.StudentAbsenceMonthDTO;
import by.vstu.dean.students.mappers.impl.V1StudentAbsenceMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentAbsenceService {

    private final V1StudentAbsenceMapperImpl v1StudentAbsenceMapperImpl;

    private final AbsenceService absenceService;


    public List<StudentAbsenceMonthDTO> findAbsencesMonthByStudent(Long caseNo) {
        Map<LocalDate, List<StudentAbsenceDTO>> map = this.v1StudentAbsenceMapperImpl
                .toDto(this.absenceService.getAbsenceByStudentCaseNo(caseNo))
                .stream().sorted(Comparator.comparing(StudentAbsenceDTO::getDate).reversed())
                .collect(Collectors.groupingBy(s -> s.getDate().withDayOfMonth(1), TreeMap::new, Collectors.toList()));

        List<StudentAbsenceMonthDTO> studentAbsenceMonthDTOS = new ArrayList<>();

        for (Map.Entry<LocalDate, List<StudentAbsenceDTO>> entry : map.entrySet()) {
            studentAbsenceMonthDTOS.add(new StudentAbsenceMonthDTO(entry.getKey().getYear(), entry.getKey().getMonth(), entry.getValue()));
        }
        return studentAbsenceMonthDTOS;
    }

    public List<StudentAbsenceDTO> findAbsencesByStudentCaseNo(Long caseNo) {
        return this.v1StudentAbsenceMapperImpl.toDto(this.absenceService.getAbsenceByStudentCaseNo(caseNo))
                .stream().sorted(Comparator.comparing(StudentAbsenceDTO::getDate).reversed()).toList();
    }
}
