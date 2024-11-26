package by.vstu.dean.core.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CoreOpenAPIConfig {

    @Value("${app.version}")
    private String version;

    @Bean
    public OpenAPI deanOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name("Authorization")))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write", "rsql")))
                .info(new Info()
                        .title("Dean API")
                        .description("API деканата ВГТУ")
                        .version(this.version)
                        .license(new License().name("VSTU").url("https://vstu.by")))
                .externalDocs(new ExternalDocumentation()
                        .description("Репозиторий")
                        .url("https://github.com/yamaxila/Dean-backend"));
    }

}
