package lasermaze.config;

import lasermaze.security.BasicAuthInterceptor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = getLogger(WebMvcConfig.class);

    @Bean
    public BasicAuthInterceptor basicAuthInterceptor() {
        return new BasicAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor());
    }
}
