package com.tyrell.replicant.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.EMPTY;


@SpringBootApplication
@EnableSwagger2
public class Application {

    @Bean
    public Docket api() {
        String requestPath = "/user.*";
        String restAPITitle = "User Service REST API";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(requestPath))
                .build()
                .apiInfo(new ApiInfo(restAPITitle, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

}