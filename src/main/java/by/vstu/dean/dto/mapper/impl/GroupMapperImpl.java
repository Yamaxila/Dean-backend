package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.BaseMapperImpl;
import by.vstu.dean.dto.future.students.GroupDTO;
import by.vstu.dean.dto.mapper.GroupMapper;
import by.vstu.dean.dto.mapper.SpecialityMapper;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.GroupModelRepository;
import by.vstu.dean.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class GroupMapperImpl extends BaseMapperImpl implements GroupMapper {

    @Autowired
    private GroupModelRepository groupModelRepository;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private SpecialityMapper specialityMapper;

    @Override
    public GroupModel toEntity(GroupDTO dto) {

        Optional<GroupModel> optionalGroupModel = this.groupModelRepository.findById(dto.getId());

        if(optionalGroupModel.isPresent())
            return optionalGroupModel.get();

        GroupModel groupModel = new GroupModel();

        groupModel.setId(dto.getId());
        groupModel.setSourceId(dto.getSourceId());
        groupModel.setUpdated(LocalDateTime.now());
        groupModel.setName(dto.getName());
        groupModel.setYearStart(dto.getYearStart());
        groupModel.setYearEnd(dto.getYearEnd());
        groupModel.setFaculty(this.facultyService.getById(dto.getFacultyId()).orElseThrow());
        groupModel.setSpec(this.specialityMapper.toEntity(dto.getSpec()));

        return groupModel;
    }

    @Override
    public GroupDTO toDto(GroupModel entity) {
        GroupDTO groupDTO = (GroupDTO) this.mapBaseFields(entity);

        groupDTO.setName(entity.getName());
        groupDTO.setYearStart(entity.getYearStart());
        groupDTO.setYearEnd(entity.getYearEnd());
        groupDTO.setSpec(this.specialityMapper.toDto(entity.getSpec()));
        groupDTO.setFacultyId(entity.getFaculty().getId());
        groupDTO.setCurrentCourse(entity.getYearEnd()-entity.getYearStart()); //TODO: Need to implement semester
        return groupDTO;
    }

    @Override
    public GroupModel partialUpdate(GroupDTO dto, GroupModel entity) {
        if (dto == null) {
            return null;
        }

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getUpdated() != null) {
            entity.setUpdated(dto.getUpdated());
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getSpec() != null) {
            if (entity.getSpec() == null) {
                entity.setSpec(null);
            }
            specialityMapper.partialUpdate(dto.getSpec(), entity.getSpec());
        }
        if (dto.getYearStart() != null) {
            entity.setYearStart(dto.getYearStart());
        }
        if (dto.getYearEnd() != null) {
            entity.setYearEnd(dto.getYearEnd());
        }

        return entity;
    }

    @Override
    public List<GroupDTO> toDto(List<GroupModel> all) {
        return all.stream().map(this::toDto).toList();
    }

    @Override
    public List<GroupModel> toEntity(List<GroupDTO> all) {
        return all.stream().map(this::toEntity).toList();
    }
}
