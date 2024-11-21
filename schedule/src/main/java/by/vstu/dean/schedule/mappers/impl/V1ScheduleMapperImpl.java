package by.vstu.dean.schedule.mappers.impl;

import by.vstu.dean.mapper.v1.impl.V1GroupMapperImpl;
import by.vstu.dean.mapper.v1.pub.impl.V1PublicTeacherMapperImpl;
import by.vstu.dean.schedule.dtos.ScheduleDTO;
import by.vstu.dean.schedule.mappers.V1ScheduleMapper;
import by.vstu.dean.timetable.models.LessonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class V1ScheduleMapperImpl implements V1ScheduleMapper {

    private final V1PublicTeacherMapperImpl teacherMapper;
    private final V1GroupMapperImpl groupMapper;

    @Override
    public LessonModel toEntity(ScheduleDTO dto) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public LessonModel partialUpdate(ScheduleDTO dto, LessonModel entity) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<LessonModel> toEntity(List<ScheduleDTO> all) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public List<ScheduleDTO> toDto(List<LessonModel> all) {
        return all.stream().map(this::toDto).toList();
    }

    @Override
    public ScheduleDTO toDto(LessonModel entity) {
        if (entity == null)
            return null;

        ScheduleDTO dto = V1ScheduleMapper.super.toDto(entity);
        dto.setGroup(entity.getGroup() != null ? groupMapper.toDto(entity.getGroup()) : null);
        dto.setTeacher(entity.getTeacher() != null ? teacherMapper.toDto(entity.getTeacher()) : null);
        dto.setCorrespondence(entity.getGroup().getFaculty().getId() == 1
                || entity.getGroup().getFaculty().getId() == 2);

        return dto;
    }
}
