package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.EducationDTO;
import by.vstu.dean.models.students.internal.EducationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EducationMapper extends BaseMapperInterface<EducationDTO, EducationModel> {

    @Override
    default EducationDTO toDto(EducationModel entity) {
        return entity == null ? null : (EducationDTO) ReflectionUtils.mapObject(entity, new EducationDTO(), false, false);
    }

}
