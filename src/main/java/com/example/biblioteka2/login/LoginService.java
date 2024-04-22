package com.example.biblioteka2.login;

import com.example.biblioteka2.user.User;
import com.example.biblioteka2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Serwis obsługujący proces logowania użytkowników do systemu.
 */
@Service
public class LoginService {
    private final PasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;

    // Klucz JWT Token pobierany z pliku konfiguracyjnego
    @Value("${jwt.token.key}")
    private String key;

    /**
     * Metoda do wyświetlania klucza JWT Token.
     */
    public void printTokenKey() {
        System.out.println("JWT Token Key: " + key);
    }

    /**
     * Konstruktor serwisu logowania.
     * @param customPasswordEncoder obiekt odpowiedzialny za kodowanie hasła użytkownika
     * @param userRepository repozytorium użytkowników
     */
    @Autowired
    public LoginService(PasswordEncoder customPasswordEncoder, UserRepository userRepository){
        this.customPasswordEncoder = customPasswordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Metoda logowania użytkownika.
     * @param loginForm formularz logowania zawierający login i hasło użytkownika
     * @return JWT Token w przypadku poprawnych danych logowania, w przeciwnym razie null
     */
    public String login(LoginForm loginForm){
        User user = userRepository.findByUsername(loginForm.getLogin());
        if(user != null && customPasswordEncoder.matches(loginForm.getPassword(), user.getPassword())){
            long timeMills = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMills))
                    .expiration(new Date(timeMills + 40 * 60 * 1000)) // Token wygasa po 40 minutach
                    .claim("id", user.getUserId()) // Dodanie id użytkownika do tokenu
                    .claim("userRole", user.getRole()) // Dodanie roli użytkownika do tokenu
                    .signWith(SignatureAlgorithm.HS256, key) // Podpisanie tokenu algorytmem HS256
                    .compact();
            return token;
        }else{
            return null;
        }
    }
}
