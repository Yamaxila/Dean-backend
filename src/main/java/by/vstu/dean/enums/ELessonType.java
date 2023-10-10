package by.vstu.dean.enums;

public enum ELessonType {

    LECTURE(0),
    PRACTICE(1),
    LAB(2),

    UNKNOWN(-1);

    final int id;

    ELessonType(int id) {
        this.id = id;
    }
}
