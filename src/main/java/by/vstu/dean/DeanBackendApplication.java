package by.vstu.dean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Главный класс для запуска приложения DeanBackendApplication.
 */
@SpringBootApplication
@EnableSwagger2
public class DeanBackendApplication {

    /**
     * Метод main для запуска приложения.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(DeanBackendApplication.class, args);
    }

}
