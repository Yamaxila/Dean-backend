package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1StudentLanguageDTO;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1StudentLanguageMapper extends BaseMapperInterface<V1StudentLanguageDTO, StudentLanguageModel> {

    @Override
    default V1StudentLanguageDTO toDto(StudentLanguageModel entity) {
        return entity == null ? null : (V1StudentLanguageDTO) ReflectionUtils.mapObject(entity, new V1StudentLanguageDTO(), false, false);
    }

}