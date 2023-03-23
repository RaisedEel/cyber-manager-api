package com.raisedeel.cybermanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().info(new Info().title("Cyber Manager API")
        .description("Cyber Manager API allows for a simple way of following and recording rentals for different computers. It also allows for saving basic information for the computers.")
        .version("v1.0")
    );
  }

}
