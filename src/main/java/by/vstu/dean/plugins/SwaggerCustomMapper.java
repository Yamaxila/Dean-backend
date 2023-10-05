package by.vstu.dean.plugins;

import io.swagger.models.Swagger;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl;

import java.util.List;


public class SwaggerCustomMapper extends ServiceModelToSwagger2MapperImpl {

    private DocumentationPluginsManager pluginsManager;
    private List<RequestHandlerProvider> handlerProviders;

    public SwaggerCustomMapper(DocumentationPluginsManager pluginsManager, List<RequestHandlerProvider> handlerProviders) {
       this.pluginsManager = pluginsManager;
       this.handlerProviders = handlerProviders;
    }

    @Override
    public Swagger mapDocumentation(Documentation documentation) {
        Swagger swagger = super.mapDocumentation(documentation);

        System.out.println(swagger.getHost());

//        swagger.setTags(tags);
        return swagger;
    }

//    @Override
//    public boolean supports(DocumentationType delimiter) {
//        return SwaggerPluginSupport.pluginDoesApply(delimiter);
//    }

}
