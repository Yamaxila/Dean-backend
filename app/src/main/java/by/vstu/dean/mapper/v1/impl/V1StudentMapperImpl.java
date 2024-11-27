package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.*;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1StudentMapperImpl implements V1StudentMapper {

    @Autowired
    private StudentService studentService;

    @Autowired
    private V1SpecializationMapper specializationMapper;

    @Autowired
    private V1HostelRoomMapper hostelRoomMapper;

    @Autowired
    private V1GroupMapper groupMapper;

    @Autowired
    private V1EducationMapper educationMapper;

    @Autowired
    private V1ParentsMapper parentsMapper;

    @Autowired
    private V1CitizenshipMapper citizenshipMapper;

    @Autowired
    private V1InstitutionMapper institutionMapper;

    @Autowired
    private V1StudentLanguageMapper languageMapper;

    @Override
    public StudentModel toEntity(V1StudentDTO dto) {

        if (dto == null)
            return null;

        StudentModel studentModel = new StudentModel();

        if (dto.getId() != null)
            studentModel = this.studentService.getById(dto.getId()).orElse(new StudentModel());

        studentModel = (StudentModel) ReflectionUtils.mapObject(studentModel, dto, true, dto.getId() != null);

        studentModel.setSpecialization(this.specializationMapper.toEntity(dto.getSpecialization()));
        studentModel.setCitizenship(this.citizenshipMapper.toEntity(dto.getCitizenship()));
        studentModel.setInstitution(this.institutionMapper.toEntity(dto.getInstitution()));
        studentModel.setStudentLanguage(this.languageMapper.toEntity(dto.getLanguage()));
        studentModel.setGroup(this.groupMapper.toEntity(dto.getGroup()));
        studentModel.setHostelRoom(this.hostelRoomMapper.toEntity(dto.getHostelRoom()));

        studentModel.setSex(dto.getSex() != null ? dto.getSex().equals("м") ? 1 : 0 : -1);

        return studentModel;
    }

    @Override
    public V1StudentDTO toDto(StudentModel entity) {
        if (entity == null) {
            return null;
        }

        V1StudentDTO studentDTO = V1StudentMapper.super.toDto(entity);

        studentDTO.setSpecialization(this.specializationMapper.toDto(entity.getSpecialization()));
        studentDTO.setInstitution(this.institutionMapper.toDto(entity.getInstitution()));
        studentDTO.setCitizenship(this.citizenshipMapper.toDto(entity.getCitizenship()));
        studentDTO.setLanguage(this.languageMapper.toDto(entity.getStudentLanguage()));
        studentDTO.setEducations(this.educationMapper.toDto(entity.getEducations()));
        studentDTO.setParents(this.parentsMapper.toDto(entity.getParents()));
        studentDTO.setGroup(this.groupMapper.toDto(entity.getGroup()));
        studentDTO.setHostelRoom(this.hostelRoomMapper.toDto(entity.getHostelRoom()));
        studentDTO.setApproved(entity.isApproved());

        studentDTO.setSex(entity.getSex() == 0 ? "ж" : "м");

        return studentDTO;
    }

    @Override
    public StudentModel partialUpdate(V1StudentDTO dto, StudentModel entity) {
        if (dto == null) {
            return null;
        }

        entity = V1StudentMapper.super.partialUpdate(dto, entity);

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