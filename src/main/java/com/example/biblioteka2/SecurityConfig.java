package com.example.biblioteka2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMhetodSecurity
public class SecurityConfig {
    @Value("${jwt.token.key}")
    private String key;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     return http
             .sessionManagement(httpSecuritySessionManagementConfigurer ->
                     httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .addFilterBefore(new JWTTTokenFilter(key),
                         UsernamePasswordAuthenticationFilter.class)
             .authorizeHttpRequests( authorizationManagerRequestMatcherRegistry ->
                     authorizationManagerRequestMatcherRegistry
                             .requestMatchers("/api/books").permitAll()

             //TUTAJ DALSZE KONFIG
             )
             //W KONFIGURACJI NIE UŻYWAMY PREFIKSU ROLE
             .build();

             }
             @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

}
