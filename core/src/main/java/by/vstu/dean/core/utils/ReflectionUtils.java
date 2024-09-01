package by.vstu.dean.core.utils;

import by.vstu.dean.core.anotations.ReflectionField;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;


/**
 * Класс для работы с ReflectionAPI.
 */
@Slf4j
public class ReflectionUtils {

    /**
     * Превращает один объект в другой с помощью ReflectionAPI.
     *
     * @param o1 - объект из которого берутся (кладутся)
     * @param o2 - объект в который кладутся (берутся) данные
     *           так же в этом объекте должны присутствовать аннотации @ReflectionField
     * @param reverseTarget - позволяет разворачивать маппинг.
     *                      При true - o1, при false - o2
     * @param partialUpdate - записывать только notnull поля
     * @return объект
     */
    public static Object mapObject(Object o1, Object o2, boolean reverseTarget, boolean partialUpdate) {

        Class<?> clazz = o1.getClass(); // Класс, откуда мы берем поля
        Class<?> targetClazz = o2.getClass(); // Класс, куда мы должны занести данные. Он же и имеет все аннотации

        log.debug("Mapping object {} to {}", clazz.getSimpleName(), targetClazz.getSimpleName());

        List<Field> tFields = getAllFields(targetClazz);
        Map<Field, Field> map = buildMap(tFields);

        log.debug("Mapped {} fields", map.size());

        for(Field field : map.keySet()) { // проходим по ключам
            Field tField = map.get(field);
            tField.setAccessible(true);
            field.setAccessible(true);

            try {
                Object value = reverseTarget ? ReflectionUtils.getValue(field, o2) : ReflectionUtils.getValue(tField, o1);
                if(reverseTarget) {
                    if(value != null || !partialUpdate)
                        ReflectionUtils.setValue(tField, value, o1);
                } else {
                    if (value != null || !partialUpdate)
                        ReflectionUtils.setValue(field, value, o2);
                }
            } catch (Exception e) {
                log.error("Can't set value for field {} in class {}", field.getName(), field.getDeclaringClass().getName());
                throw new RuntimeException(e);
            }
        }

        return reverseTarget ? o1 : o2;
    }


    /**
     * Собирает Map для метода mapObject().
     *
     * @param tFields - список полей из объекта o2 в mapObject()
     * @return Map, где ключ - поле из класса с аннотациями,
     * а значение - поле, откуда/куда будут браться/записываться данные
     */
    private static Map<Field, Field> buildMap(List<Field> tFields) {

        Map<Field, Field> map = new HashMap<>();
        tFields.stream()
                //Сразу убираем поля без аннотаций
                .filter(p -> Arrays.stream(p.getAnnotations()).anyMatch(a -> a.annotationType().equals(ReflectionField.class)))
                //Работаем с каждым полем
                .forEach(field -> {
                    //Получаем аннотацию
                    Optional<Annotation> oRf = Arrays.stream(field.getAnnotations()).filter(p -> p.annotationType().equals(ReflectionField.class)).findFirst();

                    if(oRf.isPresent()) {

                        ReflectionField rf = (ReflectionField) oRf.get();
                        //Сразу смотрим класс
                        Class<?> aClazz = rf.clazz();

                        //Если класс Class.class, то кто-то забыл указать его в аннотации
                        if(!aClazz.equals(Class.class)) {
                            //Зачем писать лишний код, если названия переменных совпадают?
                            String fName = rf.value() == null || rf.value().isEmpty() ? field.getName() : rf.value();

                            //Находим поле в классе
                            Optional<Field> oField = findField(fName, aClazz);

                            if (oField.isPresent()) {
                                //Записываем поле в Map
                                map.put(field, oField.get());
                            } else
                                log.warn("mapObject(): Cannot to find field {} in class {}", fName, aClazz.getSimpleName());
                        } else
                            log.warn("Annotation class is empty for field {}", field.getName());

                    }

                });

        return map;
    }

    /**
     * Ищет поле в классе с помощью ReflectionAPI.
     *
     * @param name - название поля.
     *             Поддерживается поиск во вложенных классах.
     *             Например: "test.field1.id"
     * @param clazz - класс, в котором мы ищем поле `name`
     * @return Optional<Field> - найденное (возможно) поле
     */
    public static Optional<Field> findField(String name, Class<?> clazz) {
        String tempName = name;
        // Получаем список полей класса.
        List<Field> fieldList = getAllFields(clazz);

        int dotPos = tempName.indexOf(".");
        String currentField = tempName.split("\\.")[0]; // сразу сохраняем текущее поле
        if(dotPos != -1) // смотрим, есть ли точка (вложенность)
            tempName = tempName.substring(dotPos + 1);

        //пробуем найти поле
        Optional<Field> tempField = fieldList.stream().filter(p -> p.getName().equals(currentField)).findFirst();

        if(tempField.isEmpty())
            return Optional.empty();

        if(dotPos != -1) // если есть вложенность, то продолжаем поиск
            return findField(tempName, tempField.get().getType());
        else // если нет - возвращаем поле
            return tempField;
    }

    public static Object findFieldAndGet(@NotNull String path, @NotNull Object o) {
        Optional<Field> field = findField(path, o.getClass());

        if(field.isEmpty())
            return null;
        field.get().setAccessible(true);

        try {
            return field.get().get(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getValue(Field field, Object o) {
        try {
            field.setAccessible(true);
            if(field.getDeclaringClass().equals(o.getClass()) || field.getDeclaringClass().equals(o.getClass().getSuperclass()))
                return field.get(o);

            Optional<Field> foundField = Arrays.stream(o.getClass().getDeclaredFields()).filter(p -> p.getType().equals(field.getDeclaringClass()) && p.getDeclaringClass().equals(field.getDeclaringClass())).findFirst();

            if(foundField.isPresent()) {
                foundField.get().setAccessible(true);
                Object value = foundField.get().get(o);
                return field.get(value);
            }

            log.warn("getValue(): Cannot to find field {} in class {}", field.getName(), o.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void setValue(Field field, Object value, Object o) {
        try {
            field.setAccessible(true);
            if(field.getDeclaringClass().equals(o.getClass()) || field.getDeclaringClass().equals(o.getClass().getSuperclass())) {
                field.set(o, value);
                return;
            }

            Optional<Field> foundField = Arrays.stream(o.getClass().getDeclaredFields()).filter(p -> p.getType().equals(field.getDeclaringClass()) && p.getDeclaringClass().equals(field.getDeclaringClass())).findFirst();

            if(foundField.isPresent()) {
                foundField.get().setAccessible(true);
                field.set(foundField.get().get(o), value);
                return;
            }

            log.warn("setValue(): Cannot to find field {} in class {}", field.getName(), o.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Собирает список всех полей класса+super-класса с помощью ReflectionAPI.
     *
     * @param clazz - класс, откуда нужно собрать список полей
     * @return список полей класса
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        //сразу собираем список полей
        List<Field> fields = new ArrayList<>(List.of(clazz.getDeclaredFields()));
        //если есть super-класс, то нужно продолжить сбор
        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }
        return fields;
    }

    /**
     * Проверяем объект на null с помощью ReflectionAPI.
     *
     * @param o - проверяемый объект
     * @return объект
     */
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
