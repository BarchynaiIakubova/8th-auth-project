package com.example.thauthproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private static final String API_KEY = "Bearer Token";

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(API_KEY, apiKeySecuritySchema())) // define the apiKey SecuritySchema
                .info(new Info().title("8th Auth-Project"))
                .security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));// then apply it. If you don't apply it will not be added to the header in cURL
    }

    public SecurityScheme apiKeySecuritySchema() {

        return new SecurityScheme()
                .name("Authorization") // authorisation-token
                .description("Just put the token")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer");
    }



//    @Bean
//    public OpenAPI openApiInformation() {
//        Server localServer = new Server()
//                .url("http://localhost:8080")
//                .description("Localhost Server URL");
//
//        Contact contact = new Contact()
//                .email("barchynai.iakubova@gmail.com")
//                .name("Barchynai Iakubova");
//
//        Info info = new Info()
//                .contact(contact)
//                .description("Spring Boot 3 + Open API 3")
//                .summary("Demo of Spring Boot 3 & Open API 3 Integration")
//                .title("8th Auth-Project")
//                .version("V1.0.0")
//                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
//
//        return new OpenAPI().info(info).addServersItem(localServer);
//    }
}
