package by.vstu.dean.enums;

public enum EMobileOperatorType {

    UNKNOWN(0, "UNK"),

    MTS(1, "МТС"),
    A1(2, "А1"),
    LIFE(3, "Life ;)");

    final String name;

    EMobileOperatorType(int ignoredId, String name) {
        this.name = name;
    }


}
