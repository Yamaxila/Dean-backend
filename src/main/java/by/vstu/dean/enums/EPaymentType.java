package by.vstu.dean.enums;

import java.time.LocalDate;
import java.util.Date;

public enum EPaymentType {

    NOT_PAID(0), PAID(1), DIRECTIONAL(2);

    int id;
    EPaymentType(int id) {
        this.id = id;
    }
}
