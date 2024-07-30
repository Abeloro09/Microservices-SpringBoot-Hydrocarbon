package com.api_calculation.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
/**
 * Configuration class for Swagger API documentation.
 * This class configures Swagger for generating API documentation.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates a {@link Docket} bean for Swagger API documentation.
     * This bean configures the base package for scanning controllers
     * and sets up Swagger with basic API information.
     *
     * @return a {@link Docket} object configured for Swagger.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api_calculation.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Provides API information for Swagger documentation.
     * This method sets the title, description, version, and other
     * details of the API documentation.
     *
     * @return an {@link ApiInfo} object containing API details.
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Microservicio Liquidacion",
                "Consumes the Analysis and Tanks microservices to perform liquidation calculations and store results in the database.",
                "v1",
                "Terms of service",
                new Contact("Abelardo Orozco", "www.example.com", "abel.oro.dev@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}