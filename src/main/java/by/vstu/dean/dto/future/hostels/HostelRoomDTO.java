package by.vstu.dean.dto.future.hostels;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.dto.future.students.StudentDTO;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class HostelRoomDTO extends BaseDTO {

    private int roomNumber;
    @Enumerated(EnumType.STRING)
    private EHostelRoomType roomType;
    private int floor;
    @Enumerated(EnumType.STRING)
    private EHostel hostel;

    private Set<StudentDTO> students;

}
