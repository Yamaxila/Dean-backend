package by.vstu.dean.students.services;

import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.services.StatementService;
import by.vstu.dean.students.dtos.StudentGradeSessionDTO;
import by.vstu.dean.students.mappers.impl.V1StudentGradeMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGradeService {
    private final StatementService statementService;
    private final V1StudentGradeMapperImpl v1StudentGradeMapperImpl;

    public List<StudentGradeSessionDTO> getStudentGradesSession(Long caseNo) {
        return this.v1StudentGradeMapperImpl.toDto(this.statementService.getAllStudentMergeForStudentByCaseNo(caseNo).stream().sorted(Comparator.comparing(StatementStudentMerge::getPassDate).reversed().thenComparing(StatementStudentMerge::getGrade)).toList());
    }
}
