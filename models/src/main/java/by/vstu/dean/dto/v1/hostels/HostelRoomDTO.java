package by.vstu.dean.dto.v1.hostels;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.models.hostels.HostelRoomModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * DTO for {@link by.vstu.dean.models.hostels.HostelRoomModel}
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "DTO для представления информации о комнате в общежитии")
public class HostelRoomDTO extends BaseDTO {

    @Schema(title = "Номер комнаты", example = "302")
    @ReflectionField(clazz = HostelRoomModel.class)
    private int roomNumber;

    @Enumerated(EnumType.STRING)
    @Schema(title = "Тип комнаты", example = "LITTLE")
    @ReflectionField(clazz = HostelRoomModel.class)
    private EHostelRoomType roomType;

    @Schema(title = "Этаж", example = "2")
    @ReflectionField(clazz = HostelRoomModel.class)
    private int floor;

    @Enumerated(EnumType.ORDINAL)
    @Schema(title = "Общежитие", example = "1")
    @ReflectionField(clazz = HostelRoomModel.class)
    private EHostel hostel;

    @Schema(title = "Cтуденты")
    private List<StudentDTO> students;
}
