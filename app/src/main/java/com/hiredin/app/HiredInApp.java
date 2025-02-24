package com.hiredin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HiredInApp {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("HiredIn API")
				.version("1.0")
				.description("API documentation for HiredIn App"));
	}

	public static void main(String[] args) {
		SpringApplication.run(HiredInApp.class, args);
	} //fhf
}
