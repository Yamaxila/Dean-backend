package by.vstu.dean.dto.mapper;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.models.specs.SpecialityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SpecialityMapper extends BaseMapperInterface<SpecialityDTO, SpecialityModel> {
}