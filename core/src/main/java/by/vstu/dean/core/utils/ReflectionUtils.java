package by.vstu.dean.core.utils;

import by.vstu.dean.core.anotations.ReflectionField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("unused")
public class ReflectionUtils {

    private static final Logger log = LoggerFactory.getLogger(ReflectionUtils.class);


    public static Object mapObject(Object o1, Object o2, boolean reverseTarget, boolean partialUpdate) {

        Class<?> clazz = o1.getClass(); // Класс, откуда мы берем поля
        Class<?> targetClazz = o2.getClass(); // Класс, куда мы должны занести данные. Он же и имеет все аннотации

        log.debug("Mapping object {} to {}", clazz.getSimpleName(), targetClazz.getSimpleName());

        List<Field> oFields = getAllFields(clazz);
        List<Field> tFields = getAllFields(targetClazz);
        Map<Field, Field> map = buildMap(tFields);

        log.debug("Mapped {} fields", map.size());

        for(Field field : map.keySet()) {
            Field tField = map.get(field);
            tField.setAccessible(true);
            field.setAccessible(true);


            try {
                if(reverseTarget) {
                    if(field.get(o2) != null || !partialUpdate)
                        tField.set(o1, field.get(o2));
                } else {
                    if (tField.get(o1) != null || !partialUpdate)
                        field.set(o2, tField.get(o1));
                }
            } catch (IllegalAccessException e) {
                log.error("Can't set value for field {} in class {}", field.getName(), field.getDeclaringClass().getName());
                throw new RuntimeException(e);
            }
        }

        return reverseTarget ? o1 : o2;
    }

    private static Map<Field, Field> buildMap(List<Field> tFields) {

        Map<Field, Field> map = new HashMap<>();
        tFields.stream()
                .filter(p -> Arrays.stream(p.getAnnotations()).anyMatch(a -> a.annotationType().equals(ReflectionField.class)))
                .forEach(field -> {
                    Optional<Annotation> oRf = Arrays.stream(field.getAnnotations()).filter(p -> p.annotationType().equals(ReflectionField.class)).findFirst();

                    if(oRf.isPresent()) {

                        ReflectionField rf = (ReflectionField) oRf.get();

                        Class<?> aClazz = rf.clazz();

                        if(!aClazz.equals(Class.class)) {
                            //Зачем писать лишний код, если названия переменных совпадают?
                            String fName = rf.value() == null || rf.value().isEmpty() ? field.getName() : rf.value();


                            Optional<Field> oField = findField(fName, aClazz);

                            if (oField.isPresent()) {
                                map.put(field, oField.get());
                            } else
                                log.warn("Cannot to find field {} in class {}", fName, aClazz.getSimpleName());
                        } else
                            log.warn("Annotation class is empty for field {}", field.getName());

                    }

                });

        return map;
    }



    public static Optional<Field> findField(String name, Class<?> clazz) {
        String tempName = name;
        List<Field> fieldList = getAllFields(clazz);

        int dotPos = tempName.indexOf(".");
        String currentField = tempName.split("\\.")[0];
        if(dotPos != -1)
            tempName = tempName.substring(dotPos + 1);

        Optional<Field> tempField = fieldList.stream().filter(p -> p.getName().equals(currentField)).findFirst();

        if(tempField.isEmpty())
            return Optional.empty();

        if(dotPos != -1)
            return findField(tempName, tempField.get().getType());
        else
            return tempField;
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>(List.of(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }
        return fields;
    }

    public static boolean validateObject(Object o) {

        if(o == null)
            return false;

        return ReflectionUtils.getAllFields(o.getClass()).stream().map(m -> {
            m.setAccessible(true);
            try {
                return m.get(o);
            } catch (IllegalAccessException e) {
                return new Object();
            }
        }).noneMatch(Objects::isNull);
    }


}
