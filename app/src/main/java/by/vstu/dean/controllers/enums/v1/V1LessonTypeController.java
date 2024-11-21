package by.vstu.dean.controllers.enums.v1;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.enums.ELessonType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/enums/lessons/")
@Tag(name = "ELessonType", description = "Тип занятия")
public class V1LessonTypeController extends EnumController<ELessonType> {
    public V1LessonTypeController() {
        this.eEnum = ELessonType.UNKNOWN;
    }
}
