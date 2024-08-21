package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.mapper.v1.CitizenshipMapper;
import by.vstu.dean.models.students.CitizenshipModel;
import org.springframework.stereotype.Component;

@Component
public class CitizenshipMapperImpl implements CitizenshipMapper {

    public CitizenshipMapperImpl() {
    }

    public CitizenshipModel toEntity(CitizenshipDTO dto) {
        if (dto == null) {
            return null;
        }

        return (CitizenshipModel) ReflectionUtils.mapObject(new CitizenshipModel(), dto, true, false);
    }
}
