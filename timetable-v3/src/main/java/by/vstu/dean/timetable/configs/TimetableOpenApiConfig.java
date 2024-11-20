package by.vstu.dean.timetable.configs;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimetableOpenApiConfig {

    @Bean
    public GroupedOpenApi v1TimetablePrivateApi() {
        return GroupedOpenApi.builder()
                .group("v1-timetable-private")
                .packagesToScan("by.vstu.dean.timetable.controllers.v1")
                .build();
    }

}
