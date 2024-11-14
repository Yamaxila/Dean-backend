package by.vstu.migrate.v1.models.hostels;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.enums.V1EHostel;
import by.vstu.migrate.v1.enums.V1EHostelRoomType;
import by.vstu.migrate.v1.models.students.V1StudentModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;

@Entity
@Table(name = "hostel_rooms")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class V1HostelRoomModel extends V1DBBaseModel {

    private int roomNumber;

    @Enumerated(EnumType.ORDINAL)
    private V1EHostelRoomType roomType;

    private int floor;

    @Enumerated(EnumType.ORDINAL)
    private V1EHostel hostel;

    @JoinColumn(referencedColumnName = "id", name = "hostel_room_id")
    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonManagedReference
    private List<V1StudentModel> students;

}
