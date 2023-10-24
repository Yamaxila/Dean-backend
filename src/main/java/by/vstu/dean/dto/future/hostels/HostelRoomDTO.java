package by.vstu.dean.dto.future.hostels;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.dto.future.students.StudentDTO;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

/**
 * DTO for {@link by.vstu.dean.future.models.hostels.HostelRoomModel}
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "DTO для представления информации о комнате в общежитии")
public class HostelRoomDTO extends BaseDTO {

    @ApiModelProperty(value = "Номер комнаты", example = "302")
    private int roomNumber;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Тип комнаты", example = "LITTLE")
    private EHostelRoomType roomType;

    @ApiModelProperty(value = "Этаж", example = "2")
    private int floor;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Общежитие", example = "HOSTEL_2")
    private EHostel hostel;

    @ApiModelProperty(value = "Список студентов в комнате")
    private Set<StudentDTO> students;
}
