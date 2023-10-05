package by.vstu.dean.plugins;

import springfox.documentation.service.ModelNamesRegistry;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ModelNamesRegistryFactoryPlugin;
import springfox.documentation.spi.service.contexts.ModelSpecificationRegistry;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

//@Component
//@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 3)
public class TestSwaggerPlugin implements ModelNamesRegistryFactoryPlugin {
    @Override
    public ModelNamesRegistry modelNamesRegistry(ModelSpecificationRegistry registry) {
        System.out.println("test");

        return null;
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return SwaggerPluginSupport.pluginDoesApply(documentationType);
    }
}
