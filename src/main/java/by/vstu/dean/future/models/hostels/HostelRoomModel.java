package by.vstu.dean.future.models.hostels;

import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.StudentModel;
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

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Тип комнаты", example = "LITTLE")
    private EHostelRoomType roomType;

    @ApiModelProperty(value = "Этаж", example = "2")
    private int floor;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Общежитие", example = "HOSTEL_2")
    private EHostel hostel;

    @JoinColumn(referencedColumnName = "id", name = "hostel_room_id")
    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(value = "Список студентов в комнате")
//    @JsonIgnore
    private Set<StudentModel> students;

}
