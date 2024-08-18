package by.vstu.dean.controllers.enums;

import by.vstu.dean.controllers.common.EnumController;
import by.vstu.dean.enums.EClassroomType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enums/classes/")
public class EClassroomController extends EnumController<EClassroomType> {
    protected EClassroomController() {
        super(EClassroomType.UNKNOWN);
    }
}
