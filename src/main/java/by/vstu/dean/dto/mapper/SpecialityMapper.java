package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.specs.SpecialityDTO;
import by.vstu.dean.future.models.specs.SpecialityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SpecialityMapper extends BaseMapperInterface<SpecialityDTO, SpecialityModel> {
}