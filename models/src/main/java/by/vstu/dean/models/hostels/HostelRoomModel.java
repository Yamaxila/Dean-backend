package by.vstu.dean.models.hostels;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.models.students.StudentModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hostel_rooms")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HostelRoomModel extends DBBaseModel {

    @ApiModelProperty(value = "Номер комнаты", example = "302")
    private int roomNumber;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Тип комнаты", example = "LITTLE")
    private EHostelRoomType roomType;

    @ApiModelProperty(value = "Этаж", example = "2")
    private int floor;

    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Общежитие", example = "1")
    private EHostel hostel;

    @JoinColumn(referencedColumnName = "id", name = "hostel_room_id")
    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(value = "Список студентов в комнате")
//    @JsonIgnore
    private Set<StudentModel> students;

}
