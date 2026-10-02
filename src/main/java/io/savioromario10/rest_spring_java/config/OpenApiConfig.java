package io.savioromario10.rest_spring_java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Sptring")
                        .version("v1")
                        .description("Using Java 17, Spring Boot 3.1.2, Maven, PostgreSQL")
                        .termsOfService("https://github.com/SavioRomario10")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/SavioRomario10")
                        ));
    }
}
