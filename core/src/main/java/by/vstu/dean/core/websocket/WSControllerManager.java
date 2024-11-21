package by.vstu.dean.core.websocket;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Менеджер WebSocket-контроллера.
 * Позволяет удобно находить контроллер по топику, модели или DTO
 */
@Component
public class WSControllerManager {

    private final Map<String, WSListener> byControllers = new LinkedHashMap<>();
    private final Map<Class<?>, WSListener> byClass = new LinkedHashMap<>();

    /**
     * Метод для регистрации контроллера в менеджере
     *
     * @param topic    - общая ссылка для контроллера. Например, "/topic/groups/"
     * @param listener - класс, имплементирующий WSListener
     */
    public void registerController(String topic, WSListener listener) {
        if (this.byControllers.containsKey(topic))
            throw new IllegalArgumentException("Controller `" + topic + "` already exists");
        this.byControllers.put(topic, listener);
    }

    /**
     * Метод для поиска контроллера по топику
     *
     * @param topic - общая ссылка для контроллера
     * @return класс, имплементирующий WSListener
     */
    public WSListener findByController(String topic) {
        return this.byControllers.getOrDefault(topic, null);
    }

    /**
     * Метод для регистрации контроллера в менеджере по классу
     *
     * @param clazz    - класс, который будет являться ключом (Entity или DTO)
     * @param listener - класс, имплементирующий WSListener
     */
    public void registerClass(Class<?> clazz, WSListener listener) {
        if (this.byClass.containsKey(clazz))
            throw new IllegalArgumentException("Class `" + clazz + "` already exists");
        this.byClass.put(clazz, listener);
    }

    /**
     * Метод для поиска контроллера по классу
     *
     * @param clazz - класс (Entity или DTO)
     * @return класс, имплементирующий WSListener
     */
    public WSListener findByClass(Class<?> clazz) {
        return this.byClass.getOrDefault(clazz, null);
    }
}
