package com.example.biblioteka2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.token.key}")
    private String key;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JWTTTokenFilter(key),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("ADMIN", "LIBRARIAN", "READER")
                                .requestMatchers(HttpMethod.GET, "/api/loans/**").hasAnyRole("ADMIN", "LIBRARIAN", "READER")
                                .requestMatchers(HttpMethod.POST, "/api/books/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/api/books/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.POST, "/api/loans/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.PUT, "/api/loans/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/api/loans/**").hasAnyRole("ADMIN", "LIBRARIAN")
                                .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )


                .sessionManagement(sessionMenegment ->
                        sessionMenegment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .build();
    }

}
