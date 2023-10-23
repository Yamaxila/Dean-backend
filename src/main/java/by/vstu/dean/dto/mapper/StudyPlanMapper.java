package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.lessons.StudyPlanDTO;
import by.vstu.dean.future.models.lessons.StudyPlanModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudyPlanMapper extends BaseMapperInterface<StudyPlanDTO, StudyPlanModel> {
}