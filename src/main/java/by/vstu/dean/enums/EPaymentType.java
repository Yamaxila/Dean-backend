package by.vstu.dean.enums;

public enum EPaymentType {

    NOT_PAID(0), PAID(1), DIRECTIONAL(2);

    int id;
    EPaymentType(int id) {
        this.id = id;
    }
}
