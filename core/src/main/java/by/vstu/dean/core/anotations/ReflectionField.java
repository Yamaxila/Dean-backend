package by.vstu.dean.core.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReflectionField {

    //Имена полей для маппинга
    String[] value() default {};

    boolean trim() default true;
}
