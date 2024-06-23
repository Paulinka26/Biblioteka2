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


    @Value("${jwt.token.key}")
    private String key;

    public void printTokenKey() {
        System.out.println("JWT Token Key: " + key);
    }

    @Autowired
    public LoginService(PasswordEncoder customPasswordEncoder, UserRepository userRepository){
        this.customPasswordEncoder = customPasswordEncoder;
        this.userRepository = userRepository;
    }


    public String login(LoginForm loginForm){
        User user = userRepository.findByUsername(loginForm.getLogin());
        if(user != null && customPasswordEncoder.matches(loginForm.getPassword(), user.getPassword())){
            long timeMills = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMills))
                    .expiration(new Date(timeMills + 40 * 60 * 1000)) // Token wygasa po 40 minutach
                    .claim("id", user.getUserId())
                    .claim("userRole", user.getRole())
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        }else{
            return null;
        }
    }
}
