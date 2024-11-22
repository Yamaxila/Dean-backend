package by.vstu.dean.configs;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для отображения API в Swagger-UI
 * {@link by.vstu.dean.core.configs.CoreOpenAPIConfig}
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Бин для отображения приватного API в Swagger-UI
     *
     * @return OpenApi-группу по пакету
     */
    @Bean
    public GroupedOpenApi v1PrivateApi() {
        return GroupedOpenApi.builder()
                .group("v1-private")
                .packagesToScan("by.vstu.dean.controllers.authorized.v1")
                .build();
    }

    /**
     * Бин для отображения публичного API в Swagger-UI
     * @return OpenApi-группу по пакету
     */
    @Bean
    public GroupedOpenApi v1PublicApi() {
        return GroupedOpenApi.builder()
                .group("v1-public")
                .packagesToScan("by.vstu.dean.controllers.enums.v1", "by.vstu.dean.controllers.nonauthorized.v1")
                .build();
    }

    /**
     * Бин для отображения API репозиториев в Swagger-UI
     * @return OpenApi-группу по пакету
     */
    @Bean
    public GroupedOpenApi v1RepoApi() {
        return GroupedOpenApi.builder()
                .group("v1-repo")
                .packagesToScan("by.vstu.dean.controllers.repo.v1")
                .build();
    }

    /**
     * Бин для отображения API для работы с файлами в Swagger-UI
     * @return OpenApi-группу по пакету
     */
    @Bean
    public GroupedOpenApi v1FilesApi() {
        return GroupedOpenApi.builder()
                .group("v1-files")
                .packagesToScan("by.vstu.dean.controllers.files")
                .build();
    }

}