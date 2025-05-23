package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
		return new OpenAPI().components(new Components()).info(apiInfo());
	}
	
	private Info apiInfo() {
		return new Info().title("Swagger Test").description("description").version("1.0.0");
	}

}
