package com.cloudengine;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 允许任何源访问，生产环境中应该更精确地指定允许的源
                .allowedMethods("*") // 允许所有HTTP方法
                .allowCredentials(true) // 允许带凭证的请求
                .maxAge(3600) // 缓存预检请求结果
                .allowedHeaders("*"); // 允许所有请求头，也可以按需指定
    }
}
