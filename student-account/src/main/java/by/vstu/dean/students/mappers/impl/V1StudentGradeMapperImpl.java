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
        return null;
    }

    @Override
    public StudentGradeDTO toDto(StatementStudentMerge entity) {
        StudentGradeDTO studentGradeDTO = V1StudentGradeMapper.super.toDto(entity);
        studentGradeDTO.setTeacherFIO(entity.getTeacher() != null ? entity.getTeacher().getFullName() : null);
        studentGradeDTO.setGrade(
                entity.getGrade().getSupportExamTypes().contains(ExamType.EXAM)
                        && entity.getGrade().getSupportGradeTypes().contains(10)
                        ? String.valueOf(entity.getGrade().getId() - 10) : entity.getGrade().getTitle()
        );
        return null;
    }

    @Override
    public StatementStudentMerge partialUpdate(StudentGradeDTO dto, StatementStudentMerge entity) {
        return V1StudentGradeMapper.super.partialUpdate(dto, entity);
    }

    @Override
    public List<StatementStudentMerge> toEntity(List<StudentGradeDTO> all) {
        return V1StudentGradeMapper.super.toEntity(all);
    }

    @Override
    public List<StudentGradeDTO> toDto(List<StatementStudentMerge> all) {
        return all.stream().map(this::toDto).toList();
    }
}
