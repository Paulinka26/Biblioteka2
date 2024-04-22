package com.example.biblioteka2.login;

/**
 * Klasa reprezentująca formularz logowania użytkownika.
 */
public class LoginForm {
    private String login; // Pole przechowujące login użytkownika
    private String password; // Pole przechowujące hasło użytkownika

    /**
     * Metoda zwracająca login użytkownika.
     * @return login użytkownika
     */
    public String getLogin() {
        return login;
    }

    /**
     * Metoda ustawiająca login użytkownika.
     * @param login nowy login użytkownika
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Metoda zwracająca hasło użytkownika.
     * @return hasło użytkownika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda ustawiająca hasło użytkownika.
     * @param password nowe hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
