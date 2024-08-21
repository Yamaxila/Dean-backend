package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.mapper.v1.FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Autowired
    private FacultyService facultyModelRepository;

    public FacultyModel toEntity(FacultyDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Optional<FacultyModel> optionalFacultyModel = this.facultyModelRepository.getById(dto.getId());

            FacultyModel facultyModel = new FacultyModel();

            if(optionalFacultyModel.isPresent())
                facultyModel = optionalFacultyModel.get();

            return (FacultyModel) ReflectionUtils.mapObject(facultyModel, dto, true, optionalFacultyModel.isPresent());
        }
    }

}
