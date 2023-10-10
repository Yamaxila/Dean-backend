package by.vstu.dean.future.models.hostels;

import by.vstu.dean.enums.EHostel;
import by.vstu.dean.enums.EHostelRoomType;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.StudentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int roomNumber;
    @Enumerated(EnumType.ORDINAL)
    private EHostelRoomType roomType;
    private int floor;

    @Enumerated(EnumType.ORDINAL)
    private EHostel hostel;

    @JoinColumn(referencedColumnName = "id", name = "hostel_room_id")
    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Set<StudentModel> students;

}
