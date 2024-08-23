package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.hostels.HostelRoomDTO;
import by.vstu.dean.mapper.v1.HostelRoomMapper;
import by.vstu.dean.models.hostels.HostelRoomModel;
import org.springframework.stereotype.Component;

@Component
public class HostelRoomMapperImpl implements HostelRoomMapper {

    public HostelRoomModel toEntity(HostelRoomDTO dto) {
        if (dto == null) {
            return null;
        }
        // Нам не нужно здесь мапить список студентов,
        // т.к. эти данные мы всё равно не должны иметь возможность занести в базу
        return (HostelRoomModel) ReflectionUtils.mapObject(new HostelRoomModel(), dto, true, false);
    }
}
