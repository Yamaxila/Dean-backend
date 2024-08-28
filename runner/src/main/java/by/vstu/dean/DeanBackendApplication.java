package by.vstu.dean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

/**
 * Главный класс для запуска приложения DeanBackendApplication.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableSwagger2
@EnableCaching
@EnableJpaRepositories("by.vstu.dean.*")
@ComponentScan(basePackages = { "by.vstu.dean.*" },
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "by.vstu.dean.old.*")})
//
@EntityScan("by.vstu.dean.*")
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
