package by.vstu.dean.mapper.v1.pub.impl;

import by.vstu.dean.dto.v1.pub.students.V1PublicStudentDTO;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.mapper.v1.pub.V1PublicStudentMapper;
import by.vstu.dean.models.students.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1PublicStudentMapperImpl implements V1PublicStudentMapper {

    @Autowired
    private V1GroupMapper groupMapper;

    @Override
    public StudentModel toEntity(V1PublicStudentDTO dto) {
        throw new RuntimeException("Public DTO cannot be used for entity updates!");
    }

    @Override
    public StudentModel partialUpdate(V1PublicStudentDTO dto, StudentModel entity) {
        throw new RuntimeException("Public DTO cannot be used for entity updates!");
    }

    @Override
    public V1PublicStudentDTO toDto(StudentModel entity) {

        if (entity == null)
            return null;

        V1PublicStudentDTO dto = V1PublicStudentMapper.super.toDto(entity);

        dto.setFirstLetterName(String.valueOf(entity.getName().isEmpty() ? "" : entity.getName().toUpperCase().charAt(0)));
        dto.setFirstLetterPatronymic(entity.getPatronymic().isEmpty() ? "" : String.valueOf(entity.getPatronymic().toUpperCase().charAt(0)));
        dto.setGroup(this.groupMapper.toDto(entity.getGroup()));

        return dto;
    }
}
