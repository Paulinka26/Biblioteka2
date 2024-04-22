package com.example.biblioteka2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler obsługujący logowanie użytkowników do systemu.
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    /**
     * Konstruktor kontrolera logowania.
     * @param loginService serwis logowania użytkowników
     */
    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    /**
     * Obsługuje żądanie logowania użytkownika.
     * @param loginForm obiekt zawierający dane logowania użytkownika
     * @return odpowiedź HTTP z tokenem uwierzytelniającym lub informacją o błędzie
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        String token = loginService.login(loginForm);
        if(token == null) {
            return new ResponseEntity<>("Incorrect login or password", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }
}
