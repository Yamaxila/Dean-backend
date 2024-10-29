package by.vstu.dean.controllers.enums.v1;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.enums.EClassroomType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enums/classes/")
@Tag(name = "EClassroom", description = "Тип аудитории")
public class V1EClassroomController extends EnumController<EClassroomType> {
    public V1EClassroomController() {
        this.eEnum = EClassroomType.UNKNOWN;
    }
}