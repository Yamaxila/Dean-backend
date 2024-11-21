package by.vstu.dean.schedule.mappers;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.schedule.dtos.ScheduleDTO;
import by.vstu.dean.timetable.models.LessonModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1ScheduleMapper extends BaseMapperInterface<ScheduleDTO, LessonModel> {

    @Override
    default ScheduleDTO toDto(LessonModel entity) {
        return entity == null ? null : (ScheduleDTO) ReflectionUtils.mapObject(entity, new ScheduleDTO(), false, false);
    }
}
