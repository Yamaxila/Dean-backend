package by.vstu.dean.schedule.services;

import by.vstu.dean.schedule.dtos.ScheduleDTO;
import by.vstu.dean.schedule.dtos.WeekDTO;
import by.vstu.dean.schedule.mappers.impl.V1ScheduleMapperImpl;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final V1ScheduleMapperImpl scheduleMapper;
    private final LessonService lessonService;

    public List<ScheduleDTO> getSchedulesByTeacher(Long teacherId) {
        return this.scheduleMapper.toDto(this.lessonService.rsql("teacher.id==" + teacherId)
                .stream().filter(this::isValid).toList());
    }

    public List<ScheduleDTO> getSchedulesByGroup(Long groupId) {
        return this.scheduleMapper.toDto(this.lessonService.rsql("group.id==" + groupId)
                .stream().filter(this::isValid).toList());
    }

    public boolean isValid(LessonModel lessonModel) {
        return lessonModel.isVisible() && LocalDate.now().isBefore(lessonModel.getEndDate().plusDays(1L));
    }

    public WeekDTO definitionOfWeek() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getMonthValue() < 7 ? currentDate.getYear() - 1 : currentDate.getYear();
        LocalDate dateStart = LocalDate.parse(year + "-09-01");

        if (dateStart.getDayOfWeek().getValue() == 7 || dateStart.getDayOfWeek().getValue() == 6) {
            if (dateStart.getDayOfWeek().getValue() == 7)
                dateStart = dateStart.plusDays(1L);
            else
                dateStart = dateStart.plusDays(2L);
        } else {
            while (dateStart.getDayOfWeek().getValue() != 1) {
                dateStart = dateStart.minusDays(1L);
            }
        }

        short weekNumber = (short) (((Math.abs(dateStart.toEpochDay() - currentDate.toEpochDay()) / 7) % 4) + 1);
        String nameOfWeek = weekNumber % 2 == 0 ? "Знаменатель" : "Числитель";
        short day = (short) LocalDate.now().getDayOfWeek().getValue();

        return new WeekDTO(weekNumber, nameOfWeek, day);
    }
}
