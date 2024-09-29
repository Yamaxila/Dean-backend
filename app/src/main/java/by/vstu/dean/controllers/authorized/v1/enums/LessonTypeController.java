package by.vstu.dean.controllers.authorized.v1.enums;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.enums.ELessonType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/enums/lessons/")
@Tag(name = "ELessonType", description = "Тип занятия")
public class LessonTypeController extends EnumController<ELessonType> {
    public LessonTypeController() {
        this.eEnum = ELessonType.UNKNOWN;
    }
}
