package by.vstu.dean.controllers.v1.enums;

import by.vstu.dean.core.controllers.EnumController;
import by.vstu.dean.core.enums.EStatus;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enums/status/")
@Api(tags = "EStatus", description = "Статус")
public class EStatusController extends EnumController<EStatus> {

    public EStatusController() {
        this.eEnum = EStatus.UNKNOWN;
    }
}
