package by.vstu.dean.enums;

public enum EStatus {

    ACTIVE(0), DELETED(1), UNKNOWN(-1);

    int id;

    EStatus(int id) {
        this.id = id;
    }
}
