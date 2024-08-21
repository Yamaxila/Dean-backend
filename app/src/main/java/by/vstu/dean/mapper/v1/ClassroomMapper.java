package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.rooms.ClassroomDTO;
import by.vstu.dean.models.rooms.ClassroomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ClassroomMapper extends BaseMapperInterface<ClassroomDTO, ClassroomModel> {

    @Override
    default ClassroomDTO toDto(ClassroomModel entity) {
        return entity == null ? null : (ClassroomDTO) ReflectionUtils.mapObject(entity, new ClassroomDTO(), false, false);
    }

}
