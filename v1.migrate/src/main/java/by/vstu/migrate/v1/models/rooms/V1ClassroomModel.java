package by.vstu.migrate.v1.models.rooms;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.enums.V1EClassroomType;
import by.vstu.migrate.v1.enums.V1EFrame;
import by.vstu.migrate.v1.models.lessons.V1DepartmentModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Setter
@Getter
public class V1ClassroomModel extends V1DBBaseModel {

    @Enumerated(EnumType.ORDINAL)
    private V1EFrame frame;
    @Enumerated(EnumType.ORDINAL)
    private V1EClassroomType roomType;
    @JoinColumn(name = "department_id")
    @ManyToOne
    private V1DepartmentModel department;
    private String roomNumber;
    private Integer seatsNumber;
    private Double square;


}
