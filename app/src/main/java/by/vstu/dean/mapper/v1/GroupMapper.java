package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.GroupDTO;
import by.vstu.dean.models.students.GroupModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupMapper extends BaseMapperInterface<GroupDTO, GroupModel> {

    @Override
    default GroupDTO toDto(GroupModel entity) {
        return entity == null ? null : (GroupDTO) ReflectionUtils.mapObject(entity, new GroupDTO(), false, false);
    }

}
