package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.FacultyDTO;
import by.vstu.dean.dto.mapper.FacultyMapper;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Autowired
    private FacultyModelRepository facultyModelRepository;

    public FacultyModel toEntity(FacultyDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Optional<FacultyModel> optionalFacultyModel = this.facultyModelRepository.findById(dto.getId());

            if(optionalFacultyModel.isPresent())
                return optionalFacultyModel.get();

            FacultyModel facultyModel = new FacultyModel();
            facultyModel.setId(dto.getId());
            facultyModel.setSourceId(dto.getSourceId());
            facultyModel.setStatus(dto.getStatus());
            facultyModel.setUpdated(dto.getUpdated());
            facultyModel.setShortName(dto.getShortName());
            facultyModel.setName(dto.getName());
            return facultyModel;
        }
    }

    public FacultyDTO toDto(FacultyModel entity) {
        if (entity == null) {
            return null;
        } else {
            FacultyDTO facultyDTO = new FacultyDTO();
            facultyDTO.setId(entity.getId());
            facultyDTO.setSourceId(entity.getSourceId());
            facultyDTO.setUpdated(entity.getUpdated());
            facultyDTO.setStatus(entity.getStatus());
            facultyDTO.setShortName(entity.getShortName());
            facultyDTO.setName(entity.getName());
            return facultyDTO;
        }
    }

    public FacultyModel partialUpdate(FacultyDTO dto, FacultyModel entity) {
        if (dto == null) {
            return null;
        } else {
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }

            if (dto.getStatus() != null) {
                entity.setStatus(dto.getStatus());
            }

            if (dto.getUpdated() != null) {
                entity.setUpdated(LocalDateTime.now());
            }

            if (dto.getShortName() != null) {
                entity.setShortName(dto.getShortName());
            }

            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }

            return entity;
        }
    }

    public List<FacultyDTO> toDto(List<FacultyModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<FacultyModel> toEntity(List<FacultyDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

}
