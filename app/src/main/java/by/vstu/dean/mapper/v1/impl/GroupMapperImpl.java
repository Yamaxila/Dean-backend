package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.GroupDTO;
import by.vstu.dean.mapper.v1.GroupMapper;
import by.vstu.dean.mapper.v1.SpecialityMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.dean.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GroupMapperImpl implements GroupMapper {

    @Autowired
    private GroupService groupService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private SpecialityMapper specialityMapper;

    @Override
    public GroupModel toEntity(GroupDTO dto) {

        if(dto == null)
            return null;


        GroupModel groupModel = new GroupModel();

        if (dto.getId() != null)
            groupModel = this.groupService.getById(dto.getId()).orElse(new GroupModel());

        groupModel = (GroupModel) ReflectionUtils.mapObject(groupModel, dto, true, dto.getId() != null);

        groupModel.setFaculty(this.facultyService.getById(dto.getFacultyId()).orElseThrow());
        groupModel.setSpec(this.specialityMapper.toEntity(dto.getSpec()));

        return groupModel;
    }

    @Override
    public GroupDTO toDto(GroupModel entity) {
        if(entity == null)
            return null;
        GroupDTO groupDTO = GroupMapper.super.toDto(entity);

        groupDTO.setSpec(this.specialityMapper.toDto(entity.getSpec()));
        int course = LocalDate.now().getYear() - entity.getYearStart() - (LocalDate.now().getMonth().getValue() < 7 ? -1 : 0);
        groupDTO.setCurrentCourse(entity.getStatus().equals(EStatus.DELETED) ? entity.getYearEnd()-entity.getYearStart() : course);

        return groupDTO;
    }

    @Override
    public GroupModel partialUpdate(GroupDTO dto, GroupModel entity) {
        if (dto == null) {
            return null;
        }

        entity = GroupMapper.super.partialUpdate(dto, entity);

        if(dto.getSpec() != null) {
            entity.setSpec(this.specialityMapper.partialUpdate(dto.getSpec(), entity.getSpec()));
        }

        return entity;
    }
}