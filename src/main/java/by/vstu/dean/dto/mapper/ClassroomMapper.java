package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.rooms.ClassroomDTO;
import by.vstu.dean.future.models.rooms.ClassroomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ClassroomMapper extends BaseMapperInterface<ClassroomDTO, ClassroomModel> {
}
