package by.vstu.dean.schedule.services;

import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.mapper.v1.impl.V1TeacherMapperImpl;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleTeacherService {
    private final LessonService lessonService;
    private final V1TeacherMapperImpl teacherMapper;
    private final ScheduleService scheduleService;

    public List<V1TeacherDTO> getValidTeachers() {
        return this.teacherMapper.toDto(this.lessonService.getAllActive(true).stream()
                .filter(scheduleService::isValid)
                .map(LessonModel::getTeacher).toList());
    }
}
