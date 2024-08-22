package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.hostels.HostelRoomDTO;
import by.vstu.dean.mapper.v1.HostelRoomMapper;
import by.vstu.dean.models.hostels.HostelRoomModel;
import org.springframework.stereotype.Component;

@Component
public class HostelRoomMapperImpl implements HostelRoomMapper {
//
//    @Autowired
//    private StudentMapper studentMapper;

    public HostelRoomModel toEntity(HostelRoomDTO dto) {
        if (dto == null) {
            return null;
        }
        // Нам не нужно здесь мапить список студентов,
        // т.к. эти данные мы всё равно не должны иметь возможность занести в базу
//            hostelRoomModel.setStudents(new HashSet<>(this.studentMapper.toEntity(dto.getStudents().stream().toList())));
        return (HostelRoomModel) ReflectionUtils.mapObject(new HostelRoomModel(), dto, true, false);
    }

    public HostelRoomDTO toDto(HostelRoomModel entity) {
        if (entity == null) {
            return null;
        }
        HostelRoomDTO hostelRoomDTO = HostelRoomMapper.super.toDto(entity);
        //Тут косяк с studentMapper
//        hostelRoomDTO.setStudents(this.studentMapper.toDto(entity.getStudents().stream().toList()));
        return hostelRoomDTO;

    }
}
