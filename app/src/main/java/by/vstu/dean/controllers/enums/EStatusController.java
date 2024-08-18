package by.vstu.dean.controllers.enums;

import by.vstu.dean.controllers.common.EnumController;
import by.vstu.dean.core.enums.EStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enums/status/")
public class EStatusController extends EnumController<EStatus> {

    public EStatusController() {
        super(EStatus.UNKNOWN);
    }
}
