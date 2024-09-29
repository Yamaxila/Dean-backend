package by.vstu.dean.controllers.enums.v1;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.core.enums.EStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enums/status/")
@Tag(name = "EStatus", description = "Статус")
public class V1EStatusController extends EnumController<EStatus> {

    public V1EStatusController() {
        this.eEnum = EStatus.UNKNOWN;
    }
}
