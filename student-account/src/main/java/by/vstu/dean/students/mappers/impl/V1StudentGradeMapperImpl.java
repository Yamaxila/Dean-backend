package by.vstu.dean.students.mappers.impl;

import by.vstu.dean.enums.ExamType;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.students.dtos.StudentGradeDTO;
import by.vstu.dean.students.mappers.V1StudentGradeMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1StudentGradeMapperImpl implements V1StudentGradeMapper {
    @Override
    public StatementStudentMerge toEntity(StudentGradeDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public StudentGradeDTO toDto(StatementStudentMerge entity) {
        if (entity == null)
            return null;

        StudentGradeDTO studentGradeDTO = V1StudentGradeMapper.super.toDto(entity);
        studentGradeDTO.setTeachers(entity.getTeachers().stream().map(m -> m.getTeacher().getFullName()).toList());
        studentGradeDTO.setGrade(
                entity.getGrade().getSupportExamTypes().contains(ExamType.EXAM)
                        && entity.getGrade().getSupportGradeTypes().contains(10) && !entity.getGrade().getSupportExamTypes().contains(ExamType.UNKNOWN)
                        ? String.valueOf(entity.getGrade().getId() - 10) : entity.getGrade().getTitle()
        );
        studentGradeDTO.setSemesterNumber(entity.getStatement().calculateSemesterNumber());
        return studentGradeDTO;
    }

    @Override
    public StatementStudentMerge partialUpdate(StudentGradeDTO dto, StatementStudentMerge entity) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<StatementStudentMerge> toEntity(List<StudentGradeDTO> all) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<StudentGradeDTO> toDto(List<StatementStudentMerge> all) {
        return all.stream().map(this::toDto).toList();
    }
}
