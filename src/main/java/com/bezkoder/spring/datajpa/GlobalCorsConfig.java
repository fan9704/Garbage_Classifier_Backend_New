package com.bezkoder.spring.datajpa;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //映射路徑
                registry.addMapping("/**")
                        .allowedOrigins("*")
//                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH", "OPTIONS","HEAD")
                        .allowedHeaders("*")
                        .allowedOriginPatterns("*");
            }

    }

