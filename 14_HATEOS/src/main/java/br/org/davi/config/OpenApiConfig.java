package br.org.davi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(new Info().title("RESTful API with java 21 and Spring boot 3").version("v1").
                description("Some description about your API").termsOfService("https://pub.davi.com.br/meus-projetos").
                license(new License().name("Apache 2.0").url("https://pub.davi.com.br/meus-projetos")));
    }
}
