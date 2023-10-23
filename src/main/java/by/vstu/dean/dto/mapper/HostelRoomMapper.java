package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.hostels.HostelRoomDTO;
import by.vstu.dean.future.models.hostels.HostelRoomModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface HostelRoomMapper extends BaseMapperInterface<HostelRoomDTO, HostelRoomModel> {
}