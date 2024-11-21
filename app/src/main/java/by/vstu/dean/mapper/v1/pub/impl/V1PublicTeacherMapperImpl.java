package by.vstu.dean.mapper.v1.pub.impl;

import by.vstu.dean.dto.v1.pub.teachers.V1PublicTeacherDTO;
import by.vstu.dean.mapper.v1.pub.V1PublicTeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1PublicTeacherMapperImpl implements V1PublicTeacherMapper {
    @Override
    public TeacherModel toEntity(V1PublicTeacherDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public TeacherModel partialUpdate(V1PublicTeacherDTO dto, TeacherModel entity) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<TeacherModel> toEntity(List<V1PublicTeacherDTO> all) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<V1PublicTeacherDTO> toDto(List<TeacherModel> all) {
        return all.stream().map(this::toDto).toList();
    }

    @Override
    public V1PublicTeacherDTO toDto(TeacherModel entity) {
        if (entity == null)
            return null;

        V1PublicTeacherDTO dto = V1PublicTeacherMapper.super.toDto(entity);
        dto.setFirstLetterName(String.valueOf(entity.getName().isEmpty() ? "" : entity.getName().toUpperCase().charAt(0)));
        dto.setFirstLetterPatronymic(entity.getPatronymic().isEmpty() ? "" : String.valueOf(entity.getPatronymic().toUpperCase().charAt(0)));
        dto.setDegree(entity.getDegree().getName());
        return dto;
    }
}
