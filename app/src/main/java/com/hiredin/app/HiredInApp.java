package com.hiredin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HiredInApp implements WebMvcConfigurer {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("HiredIn API")
                .version("1.0")
                .description("API documentation for HiredIn App")
                .contact(new Contact()
                        .name("Shaikh Masir")
                        .email("send2masir@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                    .description("Project Documentation")
                    .url("https://github.com/MohammadMasir/HiredIn"));
            		
    }
    
    public static void main(String[] args) {
        SpringApplication.run(HiredInApp.class, args);
    }
}