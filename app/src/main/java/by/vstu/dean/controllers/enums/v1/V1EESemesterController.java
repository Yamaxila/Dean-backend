package by.vstu.dean.controllers.enums.v1;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.enums.ESemester;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enums/semester/")
@Tag(name = "ESemester", description = "Семестр")
public class V1EESemesterController extends EnumController<ESemester> {
    public V1EESemesterController() {
        this.eEnum = ESemester.UNKNOWN;
    }
}
