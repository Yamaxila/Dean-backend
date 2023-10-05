package by.vstu.dean.plugins;

import by.vstu.dean.anotations.ApiSecurity;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1)
@RequiredArgsConstructor
public class SwaggerCustomPlugin implements OperationBuilderPlugin {

    private final DescriptionResolver descriptions;
    @Override
    public void apply(OperationContext context) {
        try {
            StringBuilder sb = new StringBuilder();

            Optional<RequestMapping> requestMapping = context.findAnnotation(RequestMapping.class);

            if(requestMapping.isPresent())
                sb.append("<b>Ссылка:</b>").append("<input type='text' value='").append(context.requestMappingPattern()).append("'/>").append("<br /><br />");

            Optional<ApiOperation> apiOperation = context.findAnnotation(ApiOperation.class);

            sb.append("<b>Описание</b>: ");
            if (apiOperation.isPresent()) {
                sb.append("<em>").append(apiOperation.get().notes()).append("</em>");
            } else {
                sb.append("<em>Нет</em>");
            }
            sb.append("<br /><br />");

            // Check authorization
            Optional<ApiSecurity> preAuthorizeAnnotation = context.findAnnotation(ApiSecurity.class);
            int i = 0;
            sb.append("<b>Необходимые роли</b>: ");
            if (preAuthorizeAnnotation.isPresent()) {
                if(preAuthorizeAnnotation.get().roles().length > 0)
                    sb.append("<em>");
                for (String role : preAuthorizeAnnotation.get().roles()) {
                    sb.append(role);
                    if(i < preAuthorizeAnnotation.get().roles().length-1)
                        sb.append(", ");

                    i++;
                }
                sb.append("</em>");
            } else {
                sb.append("<em>Нет</em>");
            }
            i = 0;
            sb.append("<br /><br />");
            sb.append("<b>Необходимые права</b>: ");
            if(preAuthorizeAnnotation.isPresent() && preAuthorizeAnnotation.get().scopes().length > 0) {

                if(preAuthorizeAnnotation.get().scopes().length > 0)
                    sb.append("<em>");
                for (String scope : preAuthorizeAnnotation.get().scopes()) {
                    sb.append(scope);
                    if(i < preAuthorizeAnnotation.get().scopes().length-1)
                        sb.append(", ");
                    i++;
                }
                sb.append("</em>");
            } else
                sb.append("<em>Нет</em>");


//            System.out.println(context());
            // Add the note text to the Swagger UI
            context.operationBuilder().notes(descriptions.resolve(sb.toString()));
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return SwaggerPluginSupport.pluginDoesApply(documentationType);
    }
}
