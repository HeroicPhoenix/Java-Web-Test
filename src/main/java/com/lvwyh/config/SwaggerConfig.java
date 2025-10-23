package com.lvwyh.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    /** 文档基本信息 */
    @Bean
    public OpenAPI trpgOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TRPG 游戏后端 API")
                        .description("Spring Boot + MyBatis + MySQL 接口文档")
                        .version("1.0.0")
                        .contact(new Contact().name("lvwyh").url("https://lvwyh.com").email("dev@lvwyh.com"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")))
                .servers(Arrays.asList(
                        new Server().url("/").description("默认服务")
                ));
    }

    /** 分组（可选）：只扫描 /api/** 路径 */
    @Bean
    public GroupedOpenApi trpgGroup() {
        return GroupedOpenApi.builder()
                .group("trpg")
                .pathsToMatch("/api/**")
                .build();
    }
}
