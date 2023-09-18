package by.vstu.dean.enums;

public enum EEducationType {

    DAYTIME(0), EXTRAMURAL(1), UNKNOWN(-1);

    final int id;

    EEducationType(int id) {
        this.id = id;
    }
}
