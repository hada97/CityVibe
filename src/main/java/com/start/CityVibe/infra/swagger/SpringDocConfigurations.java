package com.start.CityVibe.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API CityVibe")
                        .version("v1")
                        .description("[GitHub](https://github.com/hada97/CityVibe)\n\n"
                        ));
    }

}

/*
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))  // Definindo o token JWT
                .info(new Info()
                        .title("API CityVibe")
                        .version("v1")
                        .description("[GitHub](https://github.com/hada97/CityVibe)\n\n"
                        ));
    }
 */