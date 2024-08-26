package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.mapper.v1.FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Autowired
    private FacultyService facultyService;

    public FacultyModel toEntity(FacultyDTO dto) {
        if (dto == null) {
            return null;
        }

        FacultyModel facultyModel = new FacultyModel();

        if(dto.getId() != null)
            facultyModel = this.facultyService.getById(dto.getId()).orElse(new FacultyModel());

        return (FacultyModel) ReflectionUtils.mapObject(facultyModel, dto, true, dto.getId() != null);
    }

}
