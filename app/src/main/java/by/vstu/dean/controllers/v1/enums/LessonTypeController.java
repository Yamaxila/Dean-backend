package by.vstu.dean.controllers.v1.enums;

import by.vstu.dean.controllers.v1.EnumController;
import by.vstu.dean.enums.ELessonType;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/enums/lessons/")
@Api(tags = "ELessonType", description = "Тип занятия")
public class LessonTypeController extends EnumController<ELessonType> {
    public LessonTypeController() {
        this.eEnum = ELessonType.UNKNOWN;
    }
}
