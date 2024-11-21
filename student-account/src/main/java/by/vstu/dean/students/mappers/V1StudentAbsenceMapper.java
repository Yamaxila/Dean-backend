package by.vstu.dean.students.mappers;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.students.dtos.StudentAbsenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1StudentAbsenceMapper extends BaseMapperInterface<StudentAbsenceDTO, AbsenceModel> {

    @Override
    default StudentAbsenceDTO toDto(AbsenceModel entity) {
        return entity == null ? null : (StudentAbsenceDTO) ReflectionUtils.mapObject(entity, new StudentAbsenceDTO(), false, false);
    }
}
