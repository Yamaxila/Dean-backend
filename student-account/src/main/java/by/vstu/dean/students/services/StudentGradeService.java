package by.vstu.dean.students.services;

import by.vstu.dean.services.StatementService;
import by.vstu.dean.students.dtos.StudentGradeDTO;
import by.vstu.dean.students.mappers.impl.V1StudentGradeMapperImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGradeService {
    private final StatementService statementService;
    private final V1StudentGradeMapperImpl v1StudentGradeMapperImpl;
    private final JwtDecoder jwtDecoder;

    public List<StudentGradeDTO> getStudentGradesSession(Integer semester) {
        Long studentId = Long.parseLong(this.jwtCustomTokenDecoder("id_from_source")); //ToDo: в будущем исправить на запрос авторизации
        return v1StudentGradeMapperImpl.toDto(statementService.getAllStudentMergeForStudent(studentId).stream()
                .filter(s -> semester == null || s.getStatement().calculateSemesterNumber() == semester).toList());
    }

    public String jwtCustomTokenDecoder(String field) { //ToDo: это тоже было бы хорошо удалить
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if (requestAttributes == null)
            throw new RuntimeException("RequestAttributes cannot be null");

        HttpServletRequest request = requestAttributes.getRequest();

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null)
            throw new RuntimeException("Authorization header cannot be null");

        if (authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        }

        Jwt jwt = this.jwtDecoder.decode(authHeader);

        return jwt.getClaimAsString(field);
    }
}
