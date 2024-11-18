package by.vstu.dean.core.utils;

public class NumberUtils {

    /**
     * Позволяет преобразовывать Object в Long
     *
     * @param obj - объект, который нужно преобразовать в long
     * @return значение, если преобразование успешно и MIN_VALUE, если произошла ошибка
     */
    public static Long parseLong(Object obj) {
        try {
            if (obj instanceof Number n)
                return n.longValue();
            return Long.parseLong((String) obj);
        } catch (Exception ignored) {
            return Long.MIN_VALUE;
        }

    }

}
