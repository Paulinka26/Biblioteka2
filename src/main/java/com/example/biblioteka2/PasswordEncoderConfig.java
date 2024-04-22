package com.example.biblioteka2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Konfiguracja enkodera hasła.
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Tworzy bean enkodera BCryptPasswordEncoder.
     * @return enkoder hasła BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
