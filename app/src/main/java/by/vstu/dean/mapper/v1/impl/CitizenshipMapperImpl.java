package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.mapper.v1.CitizenshipMapper;
import by.vstu.dean.models.students.CitizenshipModel;
import by.vstu.dean.services.CitizenshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CitizenshipMapperImpl implements CitizenshipMapper {

    @Autowired
    private CitizenshipService citizenshipService;

    public CitizenshipMapperImpl() {
    }

    public CitizenshipModel toEntity(CitizenshipDTO dto) {
        if (dto == null) {
            return null;
        }

        CitizenshipModel citizenshipModel = new CitizenshipModel();

        if(dto.getId() != null)
            citizenshipModel = this.citizenshipService.getById(dto.getId()).orElse(new CitizenshipModel());

        return (CitizenshipModel) ReflectionUtils.mapObject(citizenshipModel, dto, true, dto.getId() != null);
    }
}
