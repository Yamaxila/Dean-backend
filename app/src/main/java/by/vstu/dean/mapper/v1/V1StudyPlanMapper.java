package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1StudyPlanDTO;
import by.vstu.dean.models.lessons.StudyPlanModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1StudyPlanMapper extends BaseMapperInterface<V1StudyPlanDTO, StudyPlanModel> {

    @Override
    default V1StudyPlanDTO toDto(StudyPlanModel entity) {
        return entity == null ? null : (V1StudyPlanDTO) ReflectionUtils.mapObject(entity, new V1StudyPlanDTO(), false, false);
    }

}