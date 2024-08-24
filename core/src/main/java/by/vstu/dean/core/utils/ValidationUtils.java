package by.vstu.dean.core.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class ValidationUtils {

    public static boolean isEmpty(Object... objects) {
        if(objects == null || objects.length == 0)
            return true;
        return Arrays.stream(objects).anyMatch(Objects::isNull);
    }

    public static boolean isObjectsValid(Object... objects) {
        return Arrays.stream(objects).allMatch(ValidationUtils::isObjectValid);
    }

    public static boolean isObjectValid(Object object) {

        if(object == null)
            return false;

        if (object instanceof String s) {
            return !s.isEmpty();
        }

        if (object instanceof Collection<?> collection) {
            return !collection.isEmpty();
        }

        if (object instanceof Map<?, ?> map) {
            return !map.isEmpty();
        }

        if (object instanceof Object[] array) {
            return array.length > 0;
        }

        if (object instanceof Long l) {
            return l != Long.MAX_VALUE && l != Long.MIN_VALUE;
        }

        if (object instanceof Double d) {
            return d != Double.MAX_VALUE && d != Double.MIN_VALUE;
        }

        return false;
    }

}
