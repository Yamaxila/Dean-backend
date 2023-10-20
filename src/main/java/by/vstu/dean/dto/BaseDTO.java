package by.vstu.dean.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseDTO {

    private Long id;
    private LocalDateTime updated;

}
