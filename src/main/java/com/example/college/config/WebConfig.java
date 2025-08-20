package com.example.college.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import

org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class WebConfig implements WebMvcConfigurer {
// Serve files from the local uploads/ folder at /files/**
@SuppressWarnings("null")
public void addResourceHandlers(ResourceHandlerRegistry registry) {
Path uploadDir = Paths.get("uploads");
String uploadPath = uploadDir.toFile().getAbsolutePath();
registry.addResourceHandler("/files/**")
.addResourceLocations("file:" + uploadPath + "/");
}
// Enable CORS for local HTML
@Bean
public CorsFilter corsFilter() {
CorsConfiguration config = new CorsConfiguration();
config.addAllowedOriginPattern("*");
config.addAllowedHeader("*");
config.addAllowedMethod("*");
config.setAllowCredentials(false);
UrlBasedCorsConfigurationSource source = new
UrlBasedCorsConfigurationSource();
source.registerCorsConfiguration("/**", config);
return new CorsFilter(source);
}
}