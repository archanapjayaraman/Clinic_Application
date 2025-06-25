package com.clinic.clinic.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.clinic.clinic.service.patientService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure your security filter chain here
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/patients").permitAll()
                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return http.build(); // Replace with actual security configuration
    }

    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
        // Define your user details service here
        return new JdbcUserDetailsManager(dataSource);
    }
}
// @Override
// public void addFormatters(FormatterRegistry registry) {
// System.out.println("Adding custom formatters...");
// registry.addConverter(String.class, LocalDate.class, source -> {
// if (source == null || source.isEmpty()) {
// return null;
// }
// return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
// });
// }
