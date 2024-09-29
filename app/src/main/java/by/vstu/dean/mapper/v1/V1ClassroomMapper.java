package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.rooms.V1ClassroomDTO;
import by.vstu.dean.models.rooms.ClassroomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1ClassroomMapper extends BaseMapperInterface<V1ClassroomDTO, ClassroomModel> {

    @Override
    default V1ClassroomDTO toDto(ClassroomModel entity) {
        return entity == null ? null : (V1ClassroomDTO) ReflectionUtils.mapObject(entity, new V1ClassroomDTO(), false, false);
    }

}
