package com.example.foro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivar CSRF para pruebas con Postman
                .csrf(csrf -> csrf.disable())
                // Configuración de autorizaciones
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // acceso libre a consola H2
                        .anyRequest().authenticated() // cualquier otro endpoint necesita login
                )
                // Usamos autenticación básica (user/pass en header)
                .httpBasic();

        return http.build();
    }
}
