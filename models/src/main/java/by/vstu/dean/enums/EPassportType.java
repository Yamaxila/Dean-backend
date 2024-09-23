package by.vstu.dean.enums;

public enum EPassportType {

    PASSPORT(0),

    ID_CARD(1),
    RESIDENT_CARD(2),

    FOREIGN_PASSPORT(3),
    FOREIGN_ID_CARD(4),

    UNKNOWN(-1);

    EPassportType(int ignoredId) {
    }
}
