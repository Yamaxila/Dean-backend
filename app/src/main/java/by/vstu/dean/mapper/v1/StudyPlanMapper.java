package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.StudyPlanDTO;
import by.vstu.dean.models.lessons.StudyPlanModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudyPlanMapper extends BaseMapperInterface<StudyPlanDTO, StudyPlanModel> {

    @Override
    default StudyPlanDTO toDto(StudyPlanModel entity) {
        return entity == null ? null : (StudyPlanDTO) ReflectionUtils.mapObject(entity, new StudyPlanDTO(), false, false);
    }

}