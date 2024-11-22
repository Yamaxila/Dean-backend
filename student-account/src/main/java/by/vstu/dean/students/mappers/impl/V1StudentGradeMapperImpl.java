package by.vstu.dean.students.mappers.impl;

import by.vstu.dean.enums.ExamType;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.students.dtos.StudentGradeSessionDTO;
import by.vstu.dean.students.mappers.V1StudentGradeMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1StudentGradeMapperImpl implements V1StudentGradeMapper {
    @Override
    public StatementStudentMerge toEntity(StudentGradeSessionDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public StudentGradeSessionDTO toDto(StatementStudentMerge entity) {
        if (entity == null)
            return null;

        StudentGradeSessionDTO studentGradeSessionDTO = V1StudentGradeMapper.super.toDto(entity);
        studentGradeSessionDTO.setTeachers(entity.getTeachers().stream().map(m -> m.getTeacher().getFullName()).toList());
        studentGradeSessionDTO.setGrade(
                entity.getGrade().getSupportExamTypes().contains(ExamType.EXAM)
                        && entity.getGrade().getSupportGradeTypes().contains(10) && !entity.getGrade().getSupportExamTypes().contains(ExamType.UNKNOWN)
                        ? String.valueOf(entity.getGrade().getId() - 10) : entity.getGrade().getTitle()
        );
        studentGradeSessionDTO.setSemesterNumber(entity.getStatement().calculateSemesterNumber());
        return studentGradeSessionDTO;
    }

    @Override
    public StatementStudentMerge partialUpdate(StudentGradeSessionDTO dto, StatementStudentMerge entity) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<StatementStudentMerge> toEntity(List<StudentGradeSessionDTO> all) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<StudentGradeSessionDTO> toDto(List<StatementStudentMerge> all) {
        return all.stream().map(this::toDto).toList();
    }
}
