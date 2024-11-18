package by.vstu.dean.core.websocket.enums;

public enum EPacketType {

    UNKNOWN(0, "UNKNOWN"),
    GET(1, "GET"),
    UPDATE(2, "UPDATE"),
    CREATE(3, "CREATE"),
    DELETE(4, "DELETE");

    EPacketType(int ignoredId, String ignoredName) {
    }
}
