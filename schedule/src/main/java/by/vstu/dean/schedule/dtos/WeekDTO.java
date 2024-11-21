package by.vstu.dean.schedule.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeekDTO {
    private short numberOfWeek;
    private String nameOfWeek;
    private short day;
}
