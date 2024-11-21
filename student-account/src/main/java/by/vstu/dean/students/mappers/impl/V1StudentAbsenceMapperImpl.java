package by.vstu.dean.students.mappers.impl;

import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.students.dtos.StudentAbsenceDTO;
import by.vstu.dean.students.mappers.V1StudentAbsenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1StudentAbsenceMapperImpl implements V1StudentAbsenceMapper {

    @Override
    public StudentAbsenceDTO toDto(AbsenceModel entity) {
        if (entity == null)
            return null;

        StudentAbsenceDTO studentAbsenceDto = V1StudentAbsenceMapper.super.toDto(entity);
        studentAbsenceDto.setTeacher(entity.getTeacherModel() != null ? entity.getTeacherModel().getFullName() : null);
        return studentAbsenceDto;
    }

    @Override
    public AbsenceModel toEntity(StudentAbsenceDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public AbsenceModel partialUpdate(StudentAbsenceDTO dto, AbsenceModel entity) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<AbsenceModel> toEntity(List<StudentAbsenceDTO> all) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<StudentAbsenceDTO> toDto(List<AbsenceModel> all) {
        return all.stream().map(this::toDto).toList();
    }
}
