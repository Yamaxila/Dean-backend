package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.hostels.V1HostelRoomDTO;
import by.vstu.dean.models.hostels.HostelRoomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1HostelRoomMapper extends BaseMapperInterface<V1HostelRoomDTO, HostelRoomModel> {

    @Override
    default V1HostelRoomDTO toDto(HostelRoomModel entity) {
        return entity == null ? null : (V1HostelRoomDTO) ReflectionUtils.mapObject(entity, new V1HostelRoomDTO(), false, false);
    }

}