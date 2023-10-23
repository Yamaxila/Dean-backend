package by.vstu.dean.enums;

public enum EHostel {

    HOSTEL_2(1), HOSTEL_3(2), UNKNOWN(-1);

    final int id;

    EHostel(int id) {
        this.id = id;
    }
}
