package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.mapper.v1.GroupMapper;
import by.vstu.dean.mapper.v1.HostelRoomMapper;
import by.vstu.dean.mapper.v1.SpecializationMapper;
import by.vstu.dean.mapper.v1.StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private StudentModelRepository studentModelRepository;

    @Autowired
    private SpecializationMapper specializationMapper;

    @Autowired
    private HostelRoomMapper hostelRoomMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public StudentModel toEntity(StudentDTO dto) {

        if (dto == null)
            return null;

        Optional<StudentModel> optionalStudentModel = this.studentModelRepository.findById(dto.getId());

        StudentModel studentModel = new StudentModel();

        if (optionalStudentModel.isPresent())
            studentModel = optionalStudentModel.get();

        studentModel = (StudentModel) ReflectionUtils.mapObject(studentModel, dto, true, optionalStudentModel.isPresent());

        studentModel.setSpecialization(this.specializationMapper.toEntity(dto.getSpecialization()));
        studentModel.setGroup(this.groupMapper.toEntity(dto.getGroup()));
        studentModel.setHostelRoom(this.hostelRoomMapper.toEntity(dto.getHostelRoom()));

        return studentModel;
    }

    @Override
    public StudentDTO toDto(StudentModel entity) {
        if (entity == null) {
            return null;
        }

        StudentDTO studentDTO = StudentMapper.super.toDto(entity);

        studentDTO.setSpecialization(this.specializationMapper.toDto(entity.getSpecialization()));
        studentDTO.setGroup(this.groupMapper.toDto(entity.getGroup()));
        studentDTO.setHostelRoom(this.hostelRoomMapper.toDto(entity.getHostelRoom()));
        studentDTO.setApproved(entity.isApproved());

        return studentDTO;
    }

    @Override
    public StudentModel partialUpdate(StudentDTO dto, StudentModel entity) {
        if (dto == null) {
            return null;
        }

        entity = StudentMapper.super.partialUpdate(dto, entity);

        if (dto.getSpecialization() != null) {
            entity.setSpecialization(this.specializationMapper.partialUpdate(dto.getSpecialization(), entity.getSpecialization()));
        }
        if (dto.getGroup() != null) {
            entity.setGroup(this.groupMapper.partialUpdate(dto.getGroup(), entity.getGroup()));
        }
        if (dto.getHostelRoom() != null) {
            entity.setHostelRoom(this.hostelRoomMapper.partialUpdate(dto.getHostelRoom(), entity.getHostelRoom()));
        }

        return entity;
    }
}