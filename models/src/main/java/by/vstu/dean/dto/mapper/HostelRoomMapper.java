package by.vstu.dean.dto.mapper;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.hostels.HostelRoomDTO;
import by.vstu.dean.models.hostels.HostelRoomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface HostelRoomMapper extends BaseMapperInterface<HostelRoomDTO, HostelRoomModel> {
}