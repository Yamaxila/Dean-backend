package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.models.lessons.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DepartmentMapper extends BaseMapperInterface<DepartmentDTO, DepartmentModel> {

    @Override
    default DepartmentDTO toDto(DepartmentModel entity) {
        return entity == null ? null : (DepartmentDTO) ReflectionUtils.mapObject(entity, new DepartmentDTO(), false, false);
    }

}
