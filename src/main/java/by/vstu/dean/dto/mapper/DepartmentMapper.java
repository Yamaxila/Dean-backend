package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.lessons.DepartmentDTO;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DepartmentMapper extends BaseMapperInterface<DepartmentDTO, DepartmentModel> {
}
