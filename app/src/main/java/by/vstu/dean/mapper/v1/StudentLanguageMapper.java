package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.StudentLanguageDTO;
import by.vstu.dean.models.students.StudentLanguageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentLanguageMapper extends BaseMapperInterface<StudentLanguageDTO, StudentLanguageModel> {

    @Override
    default StudentLanguageDTO toDto(StudentLanguageModel entity) {
        return entity == null ? null : (StudentLanguageDTO) ReflectionUtils.mapObject(entity, new StudentLanguageDTO(), false, false);
    }

}