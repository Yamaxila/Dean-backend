package by.vstu.dean.dto.v1.hostels;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.models.hostels.HostelRoomModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * DTO for {@link by.vstu.dean.models.hostels.HostelRoomModel}
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "DTO для представления информации о комнате в общежитии")
public class HostelRoomDTO extends BaseDTO {

    @ApiModelProperty(value = "Номер комнаты", example = "302")
    @ReflectionField(clazz = HostelRoomModel.class)
    private int roomNumber;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Тип комнаты", example = "LITTLE")
    @ReflectionField(clazz = HostelRoomModel.class)
    private EHostelRoomType roomType;

    @ApiModelProperty(value = "Этаж", example = "2")
    @ReflectionField(clazz = HostelRoomModel.class)
    private int floor;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Общежитие", example = "1")
    @ReflectionField(clazz = HostelRoomModel.class)
    private EHostel hostel;

    @ApiModelProperty(value = "Cтуденты")
    private List<StudentDTO> students;
}
