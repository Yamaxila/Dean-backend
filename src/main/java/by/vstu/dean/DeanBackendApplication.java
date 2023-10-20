package by.vstu.dean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

/**
 * Главный класс для запуска приложения DeanBackendApplication.
 */
@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class DeanBackendApplication {

    /**
     * Метод main для запуска приложения.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        SpringApplication.run(DeanBackendApplication.class, args);
    }

}
