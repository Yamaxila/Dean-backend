package by.vstu.dean.core.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SpringFoxConfig {

    @Value("${auth.url}")
    private String authURL;
    @Value("${app.version}")
    private String version;


    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("by.vstu.dean.controllers.v1.common", "by.vstu.dean.controllers.v1.enums", "by.vstu.dean.controllers.v1.students")
                .pathsToMatch("/swagger-ui/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()

                .info(new Info().title("SpringShop API")
                        .description("Spring shop sample application")
                        .version("v1.0.2")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}