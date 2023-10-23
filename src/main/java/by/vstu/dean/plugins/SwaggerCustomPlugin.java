package by.vstu.dean.plugins;

import by.vstu.dean.anotations.ApiSecurity;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Optional;

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

            if (requestMapping.isPresent()) {

                StringBuilder params = new StringBuilder();


                if (params.isEmpty() && context.getParameters().stream().anyMatch(p -> p.hasParameterAnnotation(RequestParam.class))) {
                    params.append("?");

                    int temp = 0;
                    for (ResolvedMethodParameter methodParameter : context.getParameters()) {
                        if (methodParameter.hasParameterAnnotation(RequestParam.class)) {
                            if (methodParameter.defaultName().isPresent()) {
                                String param = methodParameter.defaultName().get();
                                params.append(param).append("=").append("{").append(temp).append("}");
                                if (temp < requestMapping.get().params().length - 1)
                                    params.append("&");
                            }
                        }
                    }
                }

                sb.append("<b>Ссылка:</b>").append("<input type='text' value='").append(context.requestMappingPattern()).append(params).append("'/>").append("<br /><br />");

            }
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
                if (preAuthorizeAnnotation.get().roles().length > 0)
                    sb.append("<em>");
                for (String role : preAuthorizeAnnotation.get().roles()) {
                    sb.append(role);
                    if (i < preAuthorizeAnnotation.get().roles().length - 1)
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
            if (preAuthorizeAnnotation.isPresent() && preAuthorizeAnnotation.get().scopes().length > 0) {

                if (preAuthorizeAnnotation.get().scopes().length > 0)
                    sb.append("<em>");
                for (String scope : preAuthorizeAnnotation.get().scopes()) {
                    sb.append(scope);
                    if (i < preAuthorizeAnnotation.get().scopes().length - 1)
                        sb.append(", ");
                    i++;
                }
                sb.append("</em>");
            } else
                sb.append("<em>Нет</em>");


            // Add the note text to the Swagger UI
            context.operationBuilder().notes(descriptions.resolve(sb.toString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(@NotNull DocumentationType documentationType) {
        return SwaggerPluginSupport.pluginDoesApply(documentationType);
    }
}
