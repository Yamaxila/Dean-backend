package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.students.StudentLanguageDTO;
import by.vstu.dean.future.models.students.StudentLanguageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentLanguageMapper extends BaseMapperInterface<StudentLanguageDTO, StudentLanguageModel> {
}