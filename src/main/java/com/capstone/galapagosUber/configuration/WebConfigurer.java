package com.capstone.galapagosUber.configuration;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Objects;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfigurer implements ServletContextInitializer, WebMvcConfigurer {

    private final Environment env;
    private final ApplicationProperties applicationProperties;

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = applicationProperties.cors();

        if (Objects.nonNull(config)
                && (CollectionUtils.isNotEmpty(config.getAllowedOrigins())
                || CollectionUtils.isNotEmpty(config.getAllowedOriginPatterns()))) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/**", config);
            source.registerCorsConfiguration("/actuator/**", config);
            source.registerCorsConfiguration("/v3/api-docs", config);
            source.registerCorsConfiguration("/swagger-ui/**", config);
        }

        return new CorsFilter(source);
    }

    @Override
    public void onStartup(final ServletContext servletContext) {
        if (ArrayUtils.isNotEmpty(env.getActiveProfiles())) {
            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
        }

        log.info("Web application fully configured");
    }

    /**
     * Configure the converters to use the ISO format for dates by default.
     */
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        var registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

}


