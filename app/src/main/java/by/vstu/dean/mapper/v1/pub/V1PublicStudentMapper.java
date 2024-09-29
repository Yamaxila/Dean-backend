package by.vstu.dean.mapper.v1.pub;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.pub.students.V1PublicStudentDTO;
import by.vstu.dean.models.students.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1PublicStudentMapper extends BaseMapperInterface<V1PublicStudentDTO, StudentModel> {

    @Override
    default V1PublicStudentDTO toDto(StudentModel entity) {
        return entity == null ? null : (V1PublicStudentDTO) ReflectionUtils.mapObject(entity, new V1PublicStudentDTO(), false, false);
    }

}
