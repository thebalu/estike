package hu.elte.eotvos.estike.configuration;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OperationCustomizer getCustomizer() {
        return (operation, handlerMethod) -> operation
                .addParametersItem(new Parameter()
                        .in("header")
                        .required(true)
                        .schema(new Schema().type("string"))
                        .name("Api-Key"));
    }

}
