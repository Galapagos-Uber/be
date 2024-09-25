package com.capstone.galapagosUber.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@ConfigurationProperties(prefix = "spring.application", ignoreUnknownFields = false)
public record ApplicationProperties(
        CorsConfiguration cors
) {
}
