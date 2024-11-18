package by.vstu.dean.core.websocket;


/**
 * Слушатель для контроллера WebSocket
 */
public interface WSListener {

    /**
     * Вызывается при создании объекта
     *
     * @param data объект
     */
    void onCreate(Object data);

    /**
     * Вызывается при обновлении объекта
     *
     * @param data объект
     */
    void onUpdate(Object data);

    /**
     * Вызывается при удалении объекта
     *
     * @param data объект
     */
    void onDelete(Object data);

}
