package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1CitizenshipDTO;
import by.vstu.dean.mapper.v1.V1CitizenshipMapper;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.services.students.CitizenshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1CitizenshipMapperImpl implements V1CitizenshipMapper {

    @Autowired
    private CitizenshipService citizenshipService;

    public V1CitizenshipMapperImpl() {
    }

    public CitizenshipModel toEntity(V1CitizenshipDTO dto) {
        if (dto == null) {
            return null;
        }

        CitizenshipModel citizenshipModel = new CitizenshipModel();

        if(dto.getId() != null)
            citizenshipModel = this.citizenshipService.getById(dto.getId()).orElse(new CitizenshipModel());

        return (CitizenshipModel) ReflectionUtils.mapObject(citizenshipModel, dto, true, dto.getId() != null);
    }
}
