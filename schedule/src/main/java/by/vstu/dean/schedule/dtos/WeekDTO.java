package by.vstu.dean.schedule.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Данные о текущей недели.
 */
@Data
@AllArgsConstructor
@Schema(title = "Данные о текущей недели")
public class WeekDTO {

    /**
     * Номер недели в месяце.
     */
    @Schema(title = "Номер недели в месяце")
    private short numberOfWeek;

    /**
     * Числитель\Знаменатель.
     */
    @Schema(title = "Числитель\\Знаменатель")
    private String nameOfWeek;

    /**
     * Номер дня недели.
     */
    @Schema(title = "Номер дня недели")
    private short day;
}
