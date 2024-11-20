package by.vstu.dean.schedule.services;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.timetable.models.LessonModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ScheduleService {

    public boolean isValid(LessonModel lessonModel) {
        return lessonModel.getStatus().equals(EStatus.ACTIVE) && lessonModel.isVisible() && LocalDate.now().isBefore(lessonModel.getEndDate().plusDays(1L));
    }
}
