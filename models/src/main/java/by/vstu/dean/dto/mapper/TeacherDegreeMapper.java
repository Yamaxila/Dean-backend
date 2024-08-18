package by.vstu.dean.dto.mapper;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TeacherDegreeMapper extends BaseMapperInterface<TeacherDegreeDTO, TeacherDegreeModel> {
}