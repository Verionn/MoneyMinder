package com.minder.MoneyMinder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    private final CorsConfiguration cors;

    public AppConfig(CorsConfiguration corsConfiguration) {
        this.cors = corsConfiguration;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(cors.getMappingPattern())
                        .allowedMethods(cors.getAllowedMethods().split(","))
                        .allowedHeaders(cors.getAllowedHeaders())
                        .allowedOrigins(cors.getAllowedOrigins());
            }
        };
    }
}
