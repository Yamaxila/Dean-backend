package by.vstu.dean.enums;

public enum ESemester {

    SPRING(0),
    AUTUMN(1),

    UNKNOWN(-1);
    @SuppressWarnings("unused")
    final int id;
    ESemester(int id) {
        this.id = id;
    }
}
