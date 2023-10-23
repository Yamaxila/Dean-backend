package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.lessons.ExamTypeDTO;
import by.vstu.dean.future.models.lessons.ExamModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExamTypeMapper extends BaseMapperInterface<ExamTypeDTO, ExamModel> {
}