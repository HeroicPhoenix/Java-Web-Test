package java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI marketingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Marketing Data Governance APIs")
                        .description("API documentation for marketing data governance demo")
                        .version("1.0.0"));
    }
}
