package com.minder.MoneyMinder.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cors")
public class CorsConfiguration {

    @Value("${management.endpoints.web.cors.mapping-pattern}")
    private String mappingPattern;

    @Value("${management.endpoints.web.cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${management.endpoints.web.cors.allowed-methods}")
    private String allowedMethods;

    @Value("${management.endpoints.web.cors.allowed-headers}")
    private String allowedHeaders;

    public void setMappingPattern(String mappingPattern) {
        this.mappingPattern = mappingPattern;
    }

    public String getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public String getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public String getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public String getMappingPattern() {
        return mappingPattern;
    }

    public void setPresentation(String mappingPattern) {
        this.mappingPattern = mappingPattern;
    }
}