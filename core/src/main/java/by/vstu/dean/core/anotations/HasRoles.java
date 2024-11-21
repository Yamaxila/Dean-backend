package by.vstu.dean.core.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRoles {

    String prefix() default "ROLE_";

    String[] value() default {};

    short priority() default 0;

}
