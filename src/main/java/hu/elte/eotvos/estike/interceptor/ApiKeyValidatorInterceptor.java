package hu.elte.eotvos.estike.interceptor;

import com.google.common.base.Preconditions;
import hu.elte.eotvos.estike.exception.AuthException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiKeyValidatorInterceptor implements HandlerInterceptor {
    private final String apiKey;
    private final String swaggerPath;
    private static final String API_KEY_HEADER = "Api-Key";

    public ApiKeyValidatorInterceptor(String apiKey, String swaggerPath) {
        this.apiKey = apiKey;
        this.swaggerPath = Preconditions.checkNotNull(swaggerPath);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getRequestURI().startsWith(swaggerPath)) {
            return true;
        }

        String headerApiKey = request.getHeader(API_KEY_HEADER);
        if (headerApiKey == null) {
            throw new AuthException("Http header '" + API_KEY_HEADER + "' must be provided.");
        }

        if (!headerApiKey.equals(apiKey)) {
            throw new AuthException("Incorrect API key provided.");
        }

        return true;
    }
}
