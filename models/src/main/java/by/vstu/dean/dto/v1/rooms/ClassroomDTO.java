package by.vstu.dean.dto.v1.rooms;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.EClassroomType;
import by.vstu.dean.enums.EFrame;
import by.vstu.dean.models.rooms.ClassroomModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link ClassroomModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Аудитории")
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
