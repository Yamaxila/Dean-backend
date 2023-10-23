package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.students.CitizenshipDTO;
import by.vstu.dean.future.models.students.CitizenshipModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CitizenshipMapper extends BaseMapperInterface<CitizenshipDTO, CitizenshipModel> {

}