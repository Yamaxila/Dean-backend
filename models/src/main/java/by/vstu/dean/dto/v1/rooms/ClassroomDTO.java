package by.vstu.dean.dto.v1.rooms;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.EClassroomType;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.models.rooms.ClassroomModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * DTO for {@link by.vstu.dean.models.rooms.ClassroomModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClassroomDTO extends BaseDTO {

    @Enumerated(EnumType.STRING)
    @ReflectionField(clazz = ClassroomModel.class)
    private EFrame frame;
    @Enumerated(EnumType.STRING)
    @ReflectionField(clazz = ClassroomModel.class)
    private EClassroomType roomType;
    @ReflectionField(value = "department.id", clazz = ClassroomModel.class)
    private Long departmentId;
    @ReflectionField(clazz = ClassroomModel.class)
    private String roomNumber;
    @ReflectionField(clazz = ClassroomModel.class)
    private Integer seatsNumber;
    @ReflectionField(clazz = ClassroomModel.class)
    private Double square;

}
