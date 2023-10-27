package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.students.StudentDTO;
import by.vstu.dean.dto.mapper.GroupMapper;
import by.vstu.dean.dto.mapper.SpecializationMapper;
import by.vstu.dean.dto.mapper.StudentMapper;
import by.vstu.dean.future.models.specs.SpecializationModel;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private StudentModelRepository studentModelRepository;

    @Autowired
    private SpecializationMapper specializationMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public StudentModel toEntity(StudentDTO dto) {

        if (dto == null)
            return null;

        Optional<StudentModel> optionalStudentModel = this.studentModelRepository.findById(dto.getId());

        if (optionalStudentModel.isPresent())
            return optionalStudentModel.get();


        StudentModel.StudentModelBuilder studentModel = StudentModel.builder();

        studentModel.lastName(dto.getSurname());
        studentModel.firstName(dto.getName());
        studentModel.secondName(dto.getPatronymic());
        studentModel.sex(dto.getSex());
        studentModel.address(dto.getAddress());
        studentModel.addressCountry(dto.getAddressCountry());
        studentModel.addressIndex(dto.getAddressIndex());
        studentModel.addressState(dto.getAddressState());
        studentModel.addressRegion(dto.getAddressRegion());
        studentModel.addressCity(dto.getAddressCity());
        studentModel.addressStreet(dto.getAddressStreet());
        studentModel.addressHouse(dto.getAddressHouse());
        studentModel.addressHousePart(dto.getAddressHousePart());
        studentModel.addressFlat(dto.getAddressFlat());
        studentModel.phone(dto.getPhone());
        studentModel.specialization(this.specializationMapper.toEntity(dto.getSpecialization()));
        studentModel.group(this.groupMapper.toEntity(dto.getGroup()));
        studentModel.hostelRoom(dto.getHostelRoom());

        return studentModel.build();
    }

    @Override
    public StudentDTO toDto(StudentModel entity) {
        if (entity == null) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setSurname(entity.getLastName());
        studentDTO.setName(entity.getFirstName());
        studentDTO.setPatronymic(entity.getSecondName());
        studentDTO.setId(entity.getId());
        studentDTO.setSourceId(entity.getSourceId());
        studentDTO.setUpdated(entity.getUpdated());
        studentDTO.setStatus(entity.getStatus());
        studentDTO.setSex(entity.getSex());
        studentDTO.setAddress(entity.getAddress());
        studentDTO.setAddressCountry(entity.getAddressCountry());
        studentDTO.setAddressIndex(entity.getAddressIndex());
        studentDTO.setAddressState(entity.getAddressState());
        studentDTO.setAddressRegion(entity.getAddressRegion());
        studentDTO.setAddressCity(entity.getAddressCity());
        studentDTO.setAddressStreet(entity.getAddressStreet());
        studentDTO.setAddressHouse(entity.getAddressHouse());
        studentDTO.setAddressHousePart(entity.getAddressHousePart());
        studentDTO.setAddressFlat(entity.getAddressFlat());
        studentDTO.setPhone(entity.getPhone());
        studentDTO.setSpecialization(this.specializationMapper.toDto(entity.getSpecialization()));
        studentDTO.setGroup(this.groupMapper.toDto(entity.getGroup()));
        studentDTO.setHostelRoom(entity.getHostelRoom());
        studentDTO.setApproved(entity.isApproved());

        return studentDTO;
    }

    @Override
    public StudentModel partialUpdate(StudentDTO dto, StudentModel entity) {
        if (dto == null) {
            return null;
        }

        if (dto.getSourceId() != null) {
            entity.setSourceId(dto.getSourceId());
        }
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getUpdated() != null) {
            entity.setUpdated(dto.getUpdated());
        }
        if (dto.getSex() != null) {
            entity.setSex(dto.getSex());
        }
        if (dto.getAddress() != null) {
            entity.setAddress(dto.getAddress());
        }
        if (dto.getAddressCountry() != null) {
            entity.setAddressCountry(dto.getAddressCountry());
        }
        if (dto.getAddressIndex() != null) {
            entity.setAddressIndex(dto.getAddressIndex());
        }
        if (dto.getAddressState() != null) {
            entity.setAddressState(dto.getAddressState());
        }
        if (dto.getAddressRegion() != null) {
            entity.setAddressRegion(dto.getAddressRegion());
        }
        if (dto.getAddressCity() != null) {
            entity.setAddressCity(dto.getAddressCity());
        }
        if (dto.getAddressStreet() != null) {
            entity.setAddressStreet(dto.getAddressStreet());
        }
        if (dto.getAddressHouse() != null) {
            entity.setAddressHouse(dto.getAddressHouse());
        }
        if (dto.getAddressHousePart() != null) {
            entity.setAddressHousePart(dto.getAddressHousePart());
        }
        if (dto.getAddressFlat() != null) {
            entity.setAddressFlat(dto.getAddressFlat());
        }
        if (dto.getPhone() != null) {
            entity.setPhone(dto.getPhone());
        }
        if (dto.getSpecialization() != null) {
            if (entity.getSpecialization() == null) {
                entity.setSpecialization(new SpecializationModel());
            }
            entity.setSpecialization(this.specializationMapper.partialUpdate(dto.getSpecialization(), entity.getSpecialization()));
        }
        if (dto.getGroup() != null) {
            if (entity.getGroup() == null) {
                entity.setGroup(new GroupModel());
            }
            entity.setGroup(this.groupMapper.partialUpdate(dto.getGroup(), entity.getGroup()));
        }
        if (dto.getHostelRoom() != null) {
            entity.setHostelRoom(dto.getHostelRoom());
        }
        entity.setApproved(dto.isApproved());

        return entity;
    }

    @Override
    public List<StudentDTO> toDto(List<StudentModel> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toDto).toList();
    }

    @Override
    public List<StudentModel> toEntity(List<StudentDTO> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toEntity).toList();
    }
}