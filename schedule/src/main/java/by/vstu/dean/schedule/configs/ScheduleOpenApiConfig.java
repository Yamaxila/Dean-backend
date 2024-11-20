package by.vstu.dean.schedule.configs;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleOpenApiConfig {

    @Bean
    public GroupedOpenApi v1SchedulePublicApi() {
        return GroupedOpenApi.builder()
                .group("v1-schedule-public")
                .packagesToScan("by.vstu.dean.schedule.controllers.v1")
                .build();
    }

}
