package by.vstu.dean.dto;

import by.vstu.dean.enums.EStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseDTO {

    private Long id;
    private Long sourceId;
    private LocalDateTime updated;
    private EStatus status;

}
