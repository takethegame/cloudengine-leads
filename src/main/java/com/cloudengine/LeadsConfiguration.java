package com.cloudengine;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class LeadsConfiguration {

    @Bean
    public Docket createRestfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(appInfo())
                .groupName("leads")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloudengine.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title("cloud engine leads")
                .description("cloud engine leads api management")
                .version("1.0.0")
                .build();
    }
}
