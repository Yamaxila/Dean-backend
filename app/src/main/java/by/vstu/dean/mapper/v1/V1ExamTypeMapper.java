package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1ExamTypeDTO;
import by.vstu.dean.models.lessons.ExamModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1ExamTypeMapper extends BaseMapperInterface<V1ExamTypeDTO, ExamModel> {

    @Override
    default V1ExamTypeDTO toDto(ExamModel entity) {
        return entity == null ? null : (V1ExamTypeDTO) ReflectionUtils.mapObject(entity, new V1ExamTypeDTO(), false, false);
    }

}