package by.vstu.dean.students.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentOpenApiConfig {

    @Bean
    public GroupedOpenApi v1StudentPrivateApi() {
        return GroupedOpenApi.builder()
                .group("v1-student-private")
                .packagesToScan("by.vstu.dean.students.controllers.v1")
                .build();
    }

}
