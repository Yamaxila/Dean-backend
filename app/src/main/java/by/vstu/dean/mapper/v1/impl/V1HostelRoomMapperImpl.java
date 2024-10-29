package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.hostels.V1HostelRoomDTO;
import by.vstu.dean.mapper.v1.V1HostelRoomMapper;
import by.vstu.dean.models.hostels.HostelRoomModel;
import org.springframework.stereotype.Component;

@Component
public class V1HostelRoomMapperImpl implements V1HostelRoomMapper {

    //FIXME: А нужен ли нам тут сервис?
    public HostelRoomModel toEntity(V1HostelRoomDTO dto) {
        if (dto == null) {
            return null;
        }
        return (HostelRoomModel) ReflectionUtils.mapObject(new HostelRoomModel(), dto, true, false);
    }
}
