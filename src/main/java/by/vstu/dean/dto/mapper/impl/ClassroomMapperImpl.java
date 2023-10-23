package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.rooms.ClassroomDTO;
import by.vstu.dean.dto.mapper.ClassroomMapper;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.rooms.ClassroomModel;
import by.vstu.dean.future.repo.ClassroomModelRepository;
import by.vstu.dean.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ClassroomMapperImpl implements ClassroomMapper {

    @Autowired
    private ClassroomModelRepository classroomModelRepo;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public ClassroomModel toEntity(ClassroomDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<ClassroomModel> optionalClassroomModel = this.classroomModelRepo.findById(dto.getId());

        if(optionalClassroomModel.isPresent())
            return optionalClassroomModel.get();

        ClassroomModel classroomModel = new ClassroomModel();

        classroomModel.setId(dto.getId());
        classroomModel.setSourceId(dto.getSourceId());
        classroomModel.setStatus(dto.getStatus());
        classroomModel.setUpdated(LocalDateTime.now());
        classroomModel.setRoomNumber(dto.getRoomNumber());
        classroomModel.setSeatsNumber(dto.getSeatsNumber());
        classroomModel.setFrame(dto.getFrame());
        classroomModel.setSquare(dto.getSquare());
        classroomModel.setRoomType(dto.getRoomType());
        if(dto.getDepartmentId() != null)
            classroomModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return classroomModel;
    }

    @Override
    public ClassroomDTO toDto(ClassroomModel entity) {
        if (entity == null) {
            return null;
        }

        ClassroomDTO classroomDTO = new ClassroomDTO();

        classroomDTO.setId(entity.getId());
        classroomDTO.setSourceId(entity.getSourceId());
        classroomDTO.setUpdated(entity.getUpdated());
        classroomDTO.setStatus(entity.getStatus());
        classroomDTO.setRoomNumber(entity.getRoomNumber());
        classroomDTO.setSquare(entity.getSquare());
        if(entity.getDepartment() != null)
            classroomDTO.setDepartmentId(entity.getDepartment().getId());
        classroomDTO.setFrame(entity.getFrame());
        classroomDTO.setRoomType(entity.getRoomType());
        return classroomDTO;
    }

    @Override
    public ClassroomModel partialUpdate(ClassroomDTO dto, ClassroomModel entity) {
        if (dto == null) {
            return null;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getUpdated() != null) {
            entity.setUpdated(LocalDateTime.now());
        }
        if (dto.getFrame() != null) {
            entity.setFrame(dto.getFrame());
        }
        if (dto.getDepartmentId() != null) {
            Optional<DepartmentModel> optionalDepartmentModel = this.departmentService.getById(dto.getDepartmentId());

            if(optionalDepartmentModel.isPresent())
                entity.setDepartment(optionalDepartmentModel.get());
        }
        if (dto.getSquare() != null) {
            entity.setSquare(dto.getSquare());
        }
        if (dto.getRoomNumber() != null) {
            entity.setRoomNumber(dto.getRoomNumber());
        }
        if (dto.getRoomType() != null) {
            entity.setRoomType(dto.getRoomType());
        }

        return entity;
    }

    @Override
    public List<ClassroomDTO> toDto(List<ClassroomModel> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toDto).toList();
    }

    @Override
    public List<ClassroomModel> toEntity(List<ClassroomDTO> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toEntity).toList();
    }
}