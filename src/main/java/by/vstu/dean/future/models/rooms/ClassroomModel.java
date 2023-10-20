package by.vstu.dean.future.models.rooms;

import by.vstu.dean.enums.EClassroomType;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@Setter
@Getter
public class ClassroomModel extends DBBaseModel {

    @Enumerated(EnumType.ORDINAL)
    private EFrame frame;
    @Enumerated(EnumType.ORDINAL)
    private EClassroomType roomType;
    @JoinColumn(name = "department_id")
    @ManyToOne
    private DepartmentModel department;
    private String roomNumber;
    private Integer seatsNumber;
    private Double square;


}
