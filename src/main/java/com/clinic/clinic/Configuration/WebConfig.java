package com.clinic.clinic.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.format.FormatterRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, LocalDate.class, source -> {
            if (source == null || source.isEmpty()) {
                return null;
            }
            return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
        });
    }
}
