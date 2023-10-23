package by.vstu.dean.dto.future.rooms;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.enums.EClassroomType;
import by.vstu.dean.enums.EFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * DTO for {@link by.vstu.dean.future.models.rooms.ClassroomModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClassroomDTO extends BaseDTO {

    @Enumerated(EnumType.STRING)
    private EFrame frame;
    @Enumerated(EnumType.STRING)
    private EClassroomType roomType;
    private Long departmentId;
    private String roomNumber;
    private Integer seatsNumber;
    private Double square;

}
