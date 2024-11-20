package by.vstu.dean.schedule.services;

import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.mapper.v1.impl.V1GroupMapperImpl;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleGroupService {
    private final LessonService lessonService;
    private final ScheduleService scheduleService;
    private final V1GroupMapperImpl groupMapper;

    public List<V1GroupDTO> getValidGroups() {
        return this.groupMapper.toDto(this.lessonService.getAllActive(true).stream()
                .filter(scheduleService::isValid)
                .map(LessonModel::getGroup).toList());
    }
}
