package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.models.students.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentMapper extends BaseMapperInterface<StudentDTO, StudentModel> {

    @Override
    default StudentDTO toDto(StudentModel entity) {
        return entity == null ? null : (StudentDTO) ReflectionUtils.mapObject(entity, new StudentDTO(), false, false);
    }

}
