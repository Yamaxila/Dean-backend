package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.mapper.v1.V1SpecialityMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.dean.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class V1GroupMapperImpl implements V1GroupMapper {

    @Autowired
    private GroupService groupService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private V1SpecialityMapper specialityMapper;

    @Override
    public GroupModel toEntity(V1GroupDTO dto) {

        if(dto == null)
            return null;


        GroupModel groupModel = new GroupModel();

        if (dto.getId() != null)
            groupModel = this.groupService.getById(dto.getId()).orElse(new GroupModel());

        groupModel = (GroupModel) ReflectionUtils.mapObject(groupModel, dto, true, dto.getId() != null);

        if(dto.getFacultyId() != null)
            groupModel.setFaculty(this.facultyService.getById(dto.getFacultyId()).orElseThrow());
        if(dto.getSpec() != null)
            groupModel.setSpec(this.specialityMapper.toEntity(dto.getSpec()));

        return groupModel;
    }

    @Override
    public V1GroupDTO toDto(GroupModel entity) {
        if(entity == null)
            return null;
        V1GroupDTO groupDTO = V1GroupMapper.super.toDto(entity);

        groupDTO.setSpec(this.specialityMapper.toDto(entity.getSpec()));
        groupDTO.setFacultyId(entity.getFaculty().getId());
        return groupDTO;
    }

    @Override
    public GroupModel partialUpdate(V1GroupDTO dto, GroupModel entity) {
        if (dto == null) {
            return null;
        }

        entity = V1GroupMapper.super.partialUpdate(dto, entity);

        if(dto.getSpec() != null) {
            entity.setSpec(this.specialityMapper.partialUpdate(dto.getSpec(), entity.getSpec()));
        }

        if(dto.getFacultyId() != null) {
            Optional<FacultyModel> optionalFacultyModel = this.facultyService.getById(dto.getFacultyId());
            if (optionalFacultyModel.isPresent())
                entity.setFaculty(optionalFacultyModel.get());
        }

        return entity;
    }
}
