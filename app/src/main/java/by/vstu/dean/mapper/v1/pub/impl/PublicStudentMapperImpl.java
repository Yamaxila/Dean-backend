package by.vstu.dean.mapper.v1.pub.impl;

import by.vstu.dean.dto.v1.pub.students.PublicStudentDTO;
import by.vstu.dean.mapper.v1.GroupMapper;
import by.vstu.dean.mapper.v1.pub.PublicStudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicStudentMapperImpl implements PublicStudentMapper {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public StudentModel toEntity(PublicStudentDTO dto) {
        throw new RuntimeException("Public DTO cannot be used for entity updates!");
    }

    @Override
    public StudentModel partialUpdate(PublicStudentDTO dto, StudentModel entity) {
        throw new RuntimeException("Public DTO cannot be used for entity updates!");
    }

    @Override
    public PublicStudentDTO toDto(StudentModel entity) {

        if (entity == null)
            return null;

        PublicStudentDTO dto = PublicStudentMapper.super.toDto(entity);

        dto.setFistLetterName(String.valueOf(entity.getFirstName().isEmpty() ? "" : entity.getFirstName().toUpperCase().charAt(0)));
        dto.setFistLetterPatronymic(entity.getSecondName().isEmpty() ? "" : String.valueOf(entity.getSecondName().toUpperCase().charAt(0)));
        dto.setGroup(this.groupMapper.toDto(entity.getGroup()));

        return dto;
    }
}
