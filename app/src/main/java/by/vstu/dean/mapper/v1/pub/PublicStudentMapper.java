package by.vstu.dean.mapper.v1.pub;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.pub.students.PublicStudentDTO;
import by.vstu.dean.models.students.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PublicStudentMapper extends BaseMapperInterface<PublicStudentDTO, StudentModel> {

    @Override
    default PublicStudentDTO toDto(StudentModel entity) {
        return entity == null ? null : (PublicStudentDTO) ReflectionUtils.mapObject(entity, new PublicStudentDTO(), false, false);
    }

}
