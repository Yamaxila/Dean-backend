package by.vstu.dean.controllers.enums;

import by.vstu.dean.controllers.common.EnumController;
import by.vstu.dean.enums.ELessonType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/enums/lessons/")
public class LessonTypeController extends EnumController<ELessonType> {
    protected LessonTypeController() {
        super(ELessonType.UNKNOWN);
    }
}
