package com.enginecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        SpringApplication.run(Application.class, args);
    }

}
