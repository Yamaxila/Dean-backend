package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1DepartmentDTO;
import by.vstu.dean.models.lessons.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1DepartmentMapper extends BaseMapperInterface<V1DepartmentDTO, DepartmentModel> {

    @Override
    default V1DepartmentDTO toDto(DepartmentModel entity) {
        return entity == null ? null : (V1DepartmentDTO) ReflectionUtils.mapObject(entity, new V1DepartmentDTO(), false, false);
    }

}
