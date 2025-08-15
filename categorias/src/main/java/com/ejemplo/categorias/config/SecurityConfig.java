package com.ejemplo.categorias.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((authz) -> authz
                                                .requestMatchers(HttpMethod.GET, "/", "/login", "/authorized",
                                                                "/favicon.ico", "/error")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/categorias/**")
                                                .hasAnyAuthority("SCOPE_read", "SCOPE_write")
                                                .requestMatchers(HttpMethod.POST, "/api/categorias/**")
                                                .hasAuthority("SCOPE_write")
                                                .requestMatchers(HttpMethod.PUT, "/api/categorias/**")
                                                .hasAuthority("SCOPE_write")
                                                .requestMatchers(HttpMethod.DELETE, "/api/categorias/**")
                                                .hasAuthority("SCOPE_write")
                                                .anyRequest().authenticated())
                                .oauth2Login((login) -> login
                                                .defaultSuccessUrl("/authorized", true)
                                                .failureUrl("/login?error=true"))
                                .oauth2ResourceServer((oauth2) -> oauth2
                                                .jwt(withDefaults()));
                return http.build();
        }
}
