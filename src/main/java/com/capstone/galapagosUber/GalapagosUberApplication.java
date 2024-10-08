package com.capstone.galapagosUber;

import com.capstone.galapagosUber.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@ComponentScan(basePackages = {"com.capstone"})
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class GalapagosUberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalapagosUberApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods(CorsConfiguration.ALL)
						.allowedHeaders(CorsConfiguration.ALL)
						.allowedOriginPatterns(CorsConfiguration.ALL);
			}
		};
	}

}
