package hu.elte.eotvos.estike.configuration;

import hu.elte.eotvos.estike.interceptor.ApiKeyValidatorInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiKeyValidatorConfig implements WebMvcConfigurer {

    @Value("${security.api.key}")
    private String apiKey;

    @Value("${springdoc.swagger-ui.path:/swagger-ui}")
    private String swaggerPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiKeyValidatorInterceptor(apiKey, swaggerPath));
    }
}
