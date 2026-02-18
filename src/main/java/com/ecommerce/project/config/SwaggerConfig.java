package com.ecommerce.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme barerScheme =new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Bearer Token");
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Bearer Authorization", barerScheme))
                .addSecurityItem(securityRequirement);
    }

}
