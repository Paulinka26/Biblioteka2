package com.example.biblioteka2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Konfiguracja zabezpieczeń aplikacji.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.token.key}")
    private String key;

    /**
     * Konfiguruje łańcuch filtrów zabezpieczeń.
     * @param http obiekt konfiguracji zabezpieczeń
     * @return łańcuch filtrów zabezpieczeń
     * @throws Exception jeśli wystąpi błąd podczas konfiguracji zabezpieczeń
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTTTokenFilter(key),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books").permitAll()
                                .requestMatchers("/api/books/**").hasRole("LIBRARIAN")
                                .requestMatchers("/api/books/**").hasRole("ADMIN")
                                .requestMatchers("/api/users/**").permitAll()
                                //.requestMatchers("/api/users/**").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.POST, "/api/loans").hasRole("READER")
                                .requestMatchers(HttpMethod.GET, "/api/loans").hasRole("READER")
                                .requestMatchers("/api/loans/**").hasRole("ADMIN")
                                .requestMatchers("/api/loans/**").hasRole("LIBRARIAN")


                )
                .sessionManagement(sessionMenegment ->
                        sessionMenegment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .build();
    }

}
