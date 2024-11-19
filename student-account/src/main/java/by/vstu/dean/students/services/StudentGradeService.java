package by.vstu.dean.students.services;

import by.vstu.dean.services.StatementService;
import by.vstu.dean.students.dtos.StudentGradeDTO;
import by.vstu.dean.students.mappers.impl.V1StudentGradeMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGradeService {
    private final StatementService statementService;
    private final V1StudentGradeMapperImpl v1StudentGradeMapperImpl;

    public List<StudentGradeDTO> getStudentGradesSession(Integer semester) {
        Long studentId = 1L;
        return v1StudentGradeMapperImpl.toDto(statementService.getAllStudentMergeForStudent(studentId).stream()
                .filter(s -> semester != null ? s.getStatement().calculateSemesterNumber() == semester : null).toList());
    }
}
