package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.models.students.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1StudentMapper extends BaseMapperInterface<V1StudentDTO, StudentModel> {

    @Override
    default V1StudentDTO toDto(StudentModel entity) {
        return entity == null ? null : (V1StudentDTO) ReflectionUtils.mapObject(entity, new V1StudentDTO(), false, false);
    }

}