package by.vstu.dean.enums;

public enum ExamType {

    EXAM(0), TEST(1), UNKNOWN(-1);

    final int id;
    ExamType(int id) {
        this.id = id;
    }

}
