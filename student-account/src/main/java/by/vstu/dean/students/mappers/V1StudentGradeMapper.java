package by.vstu.dean.students.mappers;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.students.dtos.StudentGradeSessionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1StudentGradeMapper extends BaseMapperInterface<StudentGradeSessionDTO, StatementStudentMerge> {
    @Override
    default StudentGradeSessionDTO toDto(StatementStudentMerge entity){
        return entity == null ? null : (StudentGradeSessionDTO) ReflectionUtils.mapObject(entity, new StudentGradeSessionDTO(), false, false);
    }
}