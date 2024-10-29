package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.models.students.GroupModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1GroupMapper extends BaseMapperInterface<V1GroupDTO, GroupModel> {

    @Override
    default V1GroupDTO toDto(GroupModel entity) {
        return entity == null ? null : (V1GroupDTO) ReflectionUtils.mapObject(entity, new V1GroupDTO(), false, false);
    }

}
