package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.hostels.HostelRoomDTO;
import by.vstu.dean.models.hostels.HostelRoomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface HostelRoomMapper extends BaseMapperInterface<HostelRoomDTO, HostelRoomModel> {

    @Override
    default HostelRoomDTO toDto(HostelRoomModel entity) {
        return entity == null ? null : (HostelRoomDTO) ReflectionUtils.mapObject(entity, new HostelRoomDTO(), false, false);
    }

}