package by.vstu.dean.core.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, объясняющая как работать с контроллером.
 * Пример: {@link by.vstu.dean.core.websocket.controllers.BaseWSController}
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface WebSocketTopic {
    //ссылка на контроллер
    String value() default "";

    //DTO
    Class<?> dtoClass() default void.class;

    //модель
    Class<?> modelClass() default void.class;

}
